/**
 * Multi record Select Employee  To Generate Salary Component Template
 *
 */

OB = OB ||{};
OB.Payroll={
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
		     isc.PayrollSalaryCategoryProcessPopup.create({
		         recordIdList: recordIdList,
		         view: view,
		         params: params
		       }).show();
		},
		
		SalaryTemplate:function(params,view){
				params.actionHandler = 'org.wirabumi.hris.payroll.PayrollActionHandler';
			    params.adTabId = view.activeView.tabId;
			    params.adWindowId = view.windowId;
			    params.processId = '8D10EF2D20914AAA9F2D43182CDCF580';
			    OB.Payroll.execute(params,view);
			} 	    
	    };
isc.defineClass('PayrollSalaryCategoryProcessPopup',isc.OBPopup);

isc.PayrollSalaryCategoryProcessPopup.addProperties({

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
		    OB.RemoteCallManager.call('org.wirabumi.hris.payroll.PayrollActionHandler', send, {}, 
		      function (response, data, request) {
		      if (response) {
		        actionField = form.getField('SalararyCategory');
		        if (response.data) {
		          popup.setTitle('Process Request');
		          actionField.setValueMap(response.data.posibleSalaryCategory.salaryCategoryAvailable);
		        }
		      }
		    });
},		  

//define the popup inteface
initWidget: function () {

	OB.TestRegistry.register('org.wirabumi.hris.payroll.popup', this);
	var recordIdList = this.recordIdList,
	originalView = this.view,
	params = this.params;

	this.mainform = isc.DynamicForm.create({
	numCols: 3,
	colWidths: ['50%', '50%'],
	fields: [{
  	  name: 'SalararyCategory',
	  title:OB.I18N.getLabel('Pyr_SalaryCategory_ID'),
	  height: 20,
	  width: 200,
	  required: true,
	  type: '_id_17',
	  defaultToFirstOption: true 
	},{
	  	  name: 'StartingDate',
		  title:OB.I18N.getLabel('Hris_ValidDate'),
		  height: 23,
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
	var callback, action,adTabId,windowId,startingDate;
	
	callback = function (rpcResponse, data, rpcRequest) {
			var status = rpcResponse.status,
			view = rpcRequest.clientContext.originalView.getView(params.adTabId);
	  if (data.message) {
	    view.messageBar.setMessage(data.message.severity, null, data.message.text);
	  }

  rpcRequest.clientContext.popup.closeClick();
  rpcRequest.clientContext.originalView.refresh(false, false);
};

	action = this.popup.mainform.getItem('SalararyCategory').getValue();;
	startingDate =this.popup.mainform.getItem('StartingDate').getValue();
	adTabId = params.adTabId;
	windowId=params.windowId;
	
	OB.RemoteCallManager.call(params.actionHandler, {
//	DocRoutingStepId: this.popup.mainform.getItem('Action').DocRoutingStepId,
	recordIdList:recordIdList,
	action: action,adTabId:adTabId,windowId:windowId,startingDate:startingDate},
	{}, callback, {
  originalView: this.popup.view,
  popup: this.popup
});
}
});//== end of oke button

OB.TestRegistry.register('org.wirabumi.hris.payroll.okButton', this.okButton);
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