/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */

OB = OB || {};

OB.OBEDL = OB.OBEDL || {};

OB.OBEDL.CancelRequest = {
  execute: function (params, view) {
    var i, selection = params.button.contextView.viewGrid.getSelectedRecords(),
        requests = [],
        callback;

    callback = function (rpcResponse, data, rpcRequest) {

      // Close process to refresh the selected record
      params.button.contextView.messageBar.setMessage(data.message.severity, data.message.title, data.message.text);
      isc.clearPrompt();
      if (data.message.severity === 'success') {
        if (params.button.contextView.isShowingForm) {
          params.button.contextView.switchFormGridVisibility();
        }
        params.button.contextView.viewGrid.refreshGrid();
      } else {
        if (params.button.contextView.isShowingForm) {
          params.button.closeProcessPopup();
        } else {
          params.button.contextView.viewGrid.refreshGrid();
        }
      }
    };

    for (i = 0; i < selection.length; i++) {
      requests.push(selection[i].id);
    }

    isc.confirm(OB.I18N.getLabel('obedl_cancel_requests'), function (clickedOK) {
      if (clickedOK) {
        isc.showPrompt(OB.I18N.getLabel('OBUIAPP_PROCESSING') + isc.Canvas.imgHTML({
          src: OB.Styles.LoadingPrompt.loadingImage.src
        }));
        OB.RemoteCallManager.call('org.openbravo.externaldata.integration.handlers.CancelRequestHandler', {
          requests: requests
        }, {}, callback);
        params.button.contextView.viewGrid.refreshGrid();
      }
    });
  },

  cancelRequests: function (params, view) {
    OB.OBEDL.CancelRequest.execute(params, view);
  }
};