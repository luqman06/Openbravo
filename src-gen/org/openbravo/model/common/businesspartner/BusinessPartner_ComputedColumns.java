/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2013-2017 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.common.businesspartner;

import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Virtual entity class to hold computed columns for entity BusinessPartner.
 *
 * NOTE: This class should not be instantiated directly.
 */
public class BusinessPartner_ComputedColumns extends BaseOBObject implements ClientEnabled , OrganizationEnabled {
    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "BusinessPartner_ComputedColumns";
    
    public static final String PROPERTY_PYRDEDUCTIONCOMPONENT = "pYRDeductionComponent";
    public static final String PROPERTY_PYREARNINGCOMPONENT = "pYREarningComponent";
    public static final String PROPERTY_PYRSALCATEGORY = "pYRSalcategory";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public Long getPYRDeductionComponent() {
      return (Long) get(PROPERTY_PYRDEDUCTIONCOMPONENT);
    }

    public void setPYRDeductionComponent(Long pYRDeductionComponent) {
      set(PROPERTY_PYRDEDUCTIONCOMPONENT, pYRDeductionComponent);
    }
    public Long getPYREarningComponent() {
      return (Long) get(PROPERTY_PYREARNINGCOMPONENT);
    }

    public void setPYREarningComponent(Long pYREarningComponent) {
      set(PROPERTY_PYREARNINGCOMPONENT, pYREarningComponent);
    }
    public String getPYRSalcategory() {
      return (String) get(PROPERTY_PYRSALCATEGORY);
    }

    public void setPYRSalcategory(String pYRSalcategory) {
      set(PROPERTY_PYRSALCATEGORY, pYRSalcategory);
    }
    public Client getClient() {
      return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
      set(PROPERTY_CLIENT, client);
    }
    public Organization getOrganization() {
      return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
      set(PROPERTY_ORGANIZATION, organization);
    }
}
