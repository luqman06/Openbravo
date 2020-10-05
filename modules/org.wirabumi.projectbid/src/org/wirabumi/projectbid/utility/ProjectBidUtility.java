package org.wirabumi.projectbid.utility;

import java.math.BigDecimal;
import java.util.List;

import org.openbravo.dal.service.OBDal;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectProposalLine;

public class ProjectBidUtility {
	
	public static void updateProposalBidScore(Requisition requisition){
		/*
		 * asumsinya:
		 * 1. tidak ada proposal yang tidak memenuhi target qty
		 * 2. tidak ada proposal yang tidak memenuhi target item
		 * 3. linenetamt = qty di requisition * unit price di proposal line
		 * 4. score = totalline = akumulasi linenetamt untuk setiap proposal
		 */
		
		List<ProjectProposal> proposalList = requisition.getProjectProposalEmPbidRequisitionIdList();
		for (ProjectProposal proposal : proposalList){
			BigDecimal totalline = BigDecimal.ZERO;
			for (ProjectProposalLine line : proposal.getProjectProposalLineList()){
				org.wirabumi.projectbid.RequisitionLine requisitionline = line.getPbidRequisitionline();
				if (requisitionline==null)
					continue;
				RequisitionLine rl = OBDal.getInstance().get(RequisitionLine.class, requisitionline.getId());
				BigDecimal qty = rl.getQuantity();
				totalline = totalline.add(line.getPrice().multiply(qty));
			}
			proposal.setPbidScore(totalline);
			OBDal.getInstance().save(proposal);
		}
		OBDal.getInstance().commitAndClose();
	}
}
