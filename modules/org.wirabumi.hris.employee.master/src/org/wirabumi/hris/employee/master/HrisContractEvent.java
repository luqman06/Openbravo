package org.wirabumi.hris.employee.master;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.wirabumi.hris.employee.master.data.EmployeePosition;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;

public class HrisContractEvent extends EntityPersistenceEventObserver {
  private static Entity[] entities = { ModelProvider.getInstance().getEntity(
      HRIS_C_Bp_Empinfo.ENTITY_NAME) };
  protected Logger logger = Logger.getLogger(this.getClass());

  @Override
  protected Entity[] getObservedEntities() {
    return entities;
  }

  public void onUpdate(@Observes EntityUpdateEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    final HRIS_C_Bp_Empinfo Contract = (HRIS_C_Bp_Empinfo) event.getTargetInstance();

    if (Contract.isCurrentpos()) {
	Contract.getBusinessPartner().setHrisCCostcenter(Contract.getEmployeeCostCenter());
	EmployeePosition position = Contract.getPosition();
	String strPosition = null;
	if (position!=null)
		strPosition=position.getSearchKey();
	HrisContractUpdate.setCotractDetail(Contract.getBusinessPartner(),
			Contract.getEmployementtype(), Contract.getContractno(), Contract.getValidFromDate(),
			Contract.getValidToDate(), Contract.getHrisCBpDepartment(), Contract.getEchelon(),
			Contract.getLevel(), strPosition, Contract.getHrisJobtitle(),
			Contract.isPyrIspayrollmaster(), Contract.getPYRPayrollMaster(), Contract.getReportTo(),
			Contract.getSite(), Contract.getCostcenter(), Contract.getHrisContracttype(),
			Contract.isSalesRepresentative());
    } else {
      return;
    }

  }

  public void onSave(@Observes EntityNewEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    final HRIS_C_Bp_Empinfo Contract = (HRIS_C_Bp_Empinfo) event.getTargetInstance();

    if (Contract.isCurrentpos()) {
	Contract.getBusinessPartner().setHrisCCostcenter(Contract.getEmployeeCostCenter());
	EmployeePosition position = Contract.getPosition();
	String strPosition = null;
	if (position!=null)
		strPosition=position.getSearchKey();
	HrisContractUpdate.setCotractDetail(Contract.getBusinessPartner(),
			Contract.getEmployementtype(), Contract.getContractno(), Contract.getValidFromDate(),
			Contract.getValidToDate(), Contract.getHrisCBpDepartment(), Contract.getEchelon(),
			Contract.getLevel(), strPosition, Contract.getHrisJobtitle(),
			Contract.isPyrIspayrollmaster(), Contract.getPYRPayrollMaster(), Contract.getReportTo(),
			Contract.getSite(), Contract.getCostcenter(), Contract.getHrisContracttype(),
			Contract.isSalesRepresentative());
	

    } else {
      return;
    }
  }

}
