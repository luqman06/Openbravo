OB = OB ||{};
OB.HRIS={
	execute:function(params,view){
		var i,
		selection = params.button.contextView.viewGrid.getSelectedRecords(),
        recordIdList = [],
        messageBar = view.getView(params.adTabId).messageBar,
        callback, validationMessage, validationOK = true;

callback = function (rpcResponse, data, rpcRequest) {
		var status = rpcResponse.status,
	    view = rpcRequest.clientContext.view.getView(params.adTabId);
	    view.messageBar.setMessage(data.message.severity, null, data.message.text);
	    // close process to refresh current view
	    params.button.closeProcessPopup();
};
	    for (i = 0; i < selection.length; i++) {
		      recordIdList.push(selection[i].id);
		    };
		   
		// call the popup paramater
		     isc.DocumentUpdateProcessPopup.create({
		         recordIdList: recordIdList,
		         view: view,
		         params: params
		       }).show();
		},
		
		DocumentUpdate:function(params,view){
				params.actionHandler = 'org.wirabumi.hris.employee.master.HrisDocumentUpdater';
			    params.adTabId = view.activeView.tabId;
			    params.adWindowId = view.windowId;
			    params.processId = '8D10EF2D20914AAA9F2D43182CDCF580';
			    OB.HRIS.execute(params,view);
			},
			GenerateKPI: function (params,view){
				var selectRecord=params.button.contextView.viewGrid.getSelectedRecords(),
				wind=view.windowId,callback,terpilih=[],i;
			
			for(i=0;i<selectRecord.length;i++){
				terpilih.add(selectRecord[i].id);
			}
			
			callback= function(rpcResponse, data, rpcRequest){
				var j,txt='';
				isc.PopUp_Param.create({
				      view: view,
				      params: params,
				      Data:data,
				      id:terpilih
				}).show();
				
			};

			OB.RemoteCallManager.call('org.wirabumi.hris.employee.master.event.DataGenerateMeasurement', {
			      data:terpilih,command:'ambilData'
			}, {}, callback);
			}
	    };
isc.defineClass('DocumentUpdateProcessPopup',isc.OBPopup);

isc.DocumentUpdateProcessPopup.addProperties({

	  width: 320,
	  height: 200,
	  title: null,
	  showMinimizeButton: false,
	  showMaximizeButton: false,

	  //Form
	  mainform: null,

	  //Button
	  okButton: null,
	  cancelButton: null,
	  
	  getActionList: function (form) {
		    var send = {
		      recordIdList: this.recordIdList
		    },
		        actionField, popup = this;
		    send.action = 'OpenPopupParamater';
		    send.adTabId = this.params.adTabId;
		    send.windowId=this.params.adWindowId;
		    // Call The Handler
		    OB.RemoteCallManager.call('org.wirabumi.hris.employee.master.HrisDocumentUpdater', send, {}, 
		      function (response, data, request) {
		      if (response) {
		        actionField = form.getField('Action');
		        if (response.data) {
		          popup.setTitle('Process Request');
//		          actionField.DocRoutingStepId = response.data.DocRoutingStepId;
//		          actionField.setValueMap(response.data.actionComboBox.valueMap);
//		          actionField.setDefaultValue(response.data.actionComboBox.defaultValue);
		        }
		      }
		    });
},		  

//define the popup inteface
initWidget: function () {

	OB.TestRegistry.register('org.wirabumi.hris.employee.master.popup', this);
	var recordIdList = this.recordIdList,
	originalView = this.view,
	params = this.params;

	this.mainform = isc.DynamicForm.create({
	numCols: 3,
	colWidths: ['50%', '50%'],
	fields: [{
  	  name: 'ContractNo',
	  title:OB.I18N.getLabel('Hris_ContractNo'),
	  height: 20,
	  width: 200,
	  required: true,
	  type: '_id_10'
	} ,{
  	  name: 'DocDate',
	  title:OB.I18N.getLabel('Hris_DocDate'),
	  height: 25,
	  width: 200,
	  required: true,
	  type: '_id_15'
	},{
	  	  name: 'ValidDate',
		  title:OB.I18N.getLabel('Hris_ValidDate'),
		  height: 25,
		  width: 200,
		  required: true,
		  type: '_id_15'
		}]
});

//== end dynamic create form

this.okButton = isc.OBFormButton.create({
	title: OB.I18N.getLabel('OK'),
	popup: this,
	action: function () {
	var callback, action,adTabId,windowId,contractno,docdate,validdate;
	
	callback = function (rpcResponse, data, rpcRequest) {
			var status = rpcResponse.status,
			view = rpcRequest.clientContext.originalView.getView(params.adTabId);
	  if (data.message) {
	    view.messageBar.setMessage(data.message.severity, null, data.message.text);
	  }

  rpcRequest.clientContext.popup.closeClick();
  rpcRequest.clientContext.originalView.refresh(false, false);
};

	action = 'none';
	contractno= this.popup.mainform.getItem('ContractNo').getValue();
	docdate =this.popup.mainform.getItem('DocDate').getValue();
	validdate=this.popup.mainform.getItem('ValidDate').getValue();
	adTabId = params.adTabId;
	windowId=params.windowId;
	
	OB.RemoteCallManager.call(params.actionHandler, {
//	DocRoutingStepId: this.popup.mainform.getItem('Action').DocRoutingStepId,
	recordIdList:recordIdList,
	action: action,contractno:contractno,docdate:docdate,validdate:validdate,adTabId:adTabId,windowId:windowId},
	{}, callback, {
  originalView: this.popup.view,
  popup: this.popup
});
}
});//== end of oke button

OB.TestRegistry.register('org.wirabumi.hris.employee.master.popup.okButton', this.okButton);
	this.cancelButton = isc.OBFormButton.create({
		title: OB.I18N.getLabel('Cancel'),
		popup: this,
		action: function () {
			this.popup.closeClick();
		}
});

this.getActionList(this.mainform);

this.items = [
	isc.VLayout.create({
			defaultLayoutAlign: "center",
			align: "center",
			width: "100%",
			layoutMargin: 10,
			membersMargin: 6,
		members: [
		          isc.HLayout.create({
					defaultLayoutAlign: "center",
					align: "center",
					layoutMargin: 30,
					membersMargin: 6,
					members: this.mainform
					}),
				isc.HLayout.create({
				defaultLayoutAlign: "center",
				align: "center",
				membersMargin: 10,
				members: [this.okButton, this.cancelButton]
					})]
		})];
				this.Super('initWidget', arguments);
	}
});
