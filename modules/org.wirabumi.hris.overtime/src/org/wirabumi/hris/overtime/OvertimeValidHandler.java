package org.wirabumi.hris.overtime;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.event.Observes;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.gen.oez.utility.DateTimeUtility;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class OvertimeValidHandler extends EntityPersistenceEventObserver {
  private static Entity[] entities = { ModelProvider.getInstance().getEntity(
      ot_overtime.ENTITY_NAME) };

  @Override
  protected Entity[] getObservedEntities() {
    return entities;
  }

  public void onUpdate(@Observes EntityUpdateEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    ot_overtime overtimeRequest = (ot_overtime) event.getTargetInstance();
    String Status = overtimeRequest.getDocumentStatus();
    if (!"CO".equalsIgnoreCase(Status) && !"CL".equalsIgnoreCase(Status)) {
      validRequest(overtimeRequest);
    }
  }

  public void onSave(@Observes EntityNewEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    ot_overtime overtimeRequest = (ot_overtime) event.getTargetInstance();
    validRequest(overtimeRequest);
  }

  private boolean validRequest(ot_overtime overtimeRequest) {
	if (overtimeRequest.isManual())
		return true;
    boolean valid = true;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String language = OBContext.getOBContext().getLanguage().getLanguage();
    ConnectionProvider conn = new DalConnectionProvider(false);

    BusinessPartner employee = overtimeRequest.getBusinessPartner();
    Date startDate = overtimeRequest.getStartingDate();
    Date endDate = overtimeRequest.getEndingDate();
    Date CheckIn = overtimeRequest.getCheckin();
    Date CheckOut = overtimeRequest.getCheckout();
    Date ovtRequestIn = null, ovtRequestOut = null;
    try {
      ovtRequestIn = formatDate.parse(DateIntervalUtility.getDate(startDate, "dd-MM-yyyy")
          .concat("  ").concat(DateIntervalUtility.getTime(CheckIn)));
      ovtRequestOut = formatDate.parse(DateIntervalUtility.getDate(endDate, "dd-MM-yyyy")
          .concat(" ").concat(DateIntervalUtility.getTime(CheckOut)));

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (AttendanceUtility.isShiftPresent(ovtRequestIn, employee.getId())) {
      Date CheckOutTimeStamp = AttendanceUtility.workingFinishSwitced(ovtRequestIn, employee);
      Date CheckInTimeStamp = AttendanceUtility.workingStartSwitced(ovtRequestIn, employee);
      if (CheckInTimeStamp == null && CheckOutTimeStamp == null) {
        CheckOutTimeStamp = ovtRequestIn;
        CheckInTimeStamp = ovtRequestIn;
      }
      boolean nonBusinessDay = AttendanceUtility.isNonBusinessDay(startDate);
      boolean isOVerlap = DateTimeUtility.isOverlap(ovtRequestIn, ovtRequestOut, CheckInTimeStamp,
          CheckOutTimeStamp, true);
      boolean isOvertimeExist = org.wirabumi.hris.overtime.utility.Utility.isOvertimeOverlaps(
          employee, overtimeRequest, startDate, endDate, ovtRequestIn, ovtRequestOut);
      if (nonBusinessDay) {
        valid = false;
      } else if (isOVerlap) {
        valid = false;
        throw new OBException(Utility.messageBD(conn, "Pengajuan Lembur Melanggar Jam Kerja.",
            language));
      } else if (isOvertimeExist) {
        valid = false;
        throw new OBException(Utility.messageBD(conn,
            "Pengajuan Lembur Telah Ada Sebelumnya Silahkan Periksa Kembali", language));
      }
    } else {
      valid = false;
      throw new OBException(Utility.messageBD(conn, "Karyawan Tidak Memiliki Jadwal Kerja.",
          language));
    }
    return valid;
  }
}
