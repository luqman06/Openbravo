OB=OB ||{};
OB.LV={
		ProcessCuti: function(params, view){
			var selectRecord=params.button.contextView.viewGrid.getSelectedRecords(),
			wind=view.windowId,callback,terpilih=[],i;
			
			for(i=0;i<selectRecord.length;i++){
				terpilih.add(selectRecord[i].id);
			}
			
			callback= function(rpcResponse, data, rpcRequest){
				var j,txt='';
				isc.DocumentCuti.create({
				      view: view,
				      params: params,
				      Data:data,
				      id:terpilih
				}).show();
			};
			
			OB.RemoteCallManager.call('org.wirabumi.hris.leave.ad_process.ProcessCutiHandler', {
			      data:terpilih, command:'ambilData'
			}, {}, callback);
		}
};

isc.defineClass('DocumentCuti',isc.OBPopup);

isc.DocumentCuti.addProperties({
	width: 300,
	height: 200,
	title: "Document Cuti Bersama",
	showMinimizeButton: false,
	showMaximizeButton: false,
	initWidget: function(){
		var me=this;
		//OK Button
		this.okButton = isc.OBFormButton.create({
			title: OB.I18N.getLabel('OK'),
			popup: this,
			click: function () {
				var callback, i;
			
				callback= function(rpcResponse, data, rpcRequest){
					var View=rpcRequest.clientContext.originalView.getView(rpcResponse.clientContext.originalView.view.tabId);
//					View.messageBar.setMessage(data.type, data.title, data.message);
					me.closeClick();
					rpcRequest.clientContext.originalView.refresh(false, false);
				};
				
				OB.RemoteCallManager.call('org.wirabumi.hris.leave.ad_process.ProcessCutiHandler',{
					idMassLeave:me.id,command:'setData'
				},{},callback,{originalView: me.view});
			}
		});
		
		//Cancel Button
		OB.TestRegistry.register('org.wirabumi.gen.oez.popup.okButton', this.okButton);
		this.cancelButton = isc.OBFormButton.create({
			title: OB.I18N.getLabel('Cancel'),
			popup:this,
			action: function () {
				this.popup.closeClick();
			}
		});
		
		//Element di layout
		this.items = [
		  isc.VLayout.create({
			  defaultLayoutAlign: "center",
			  align: "center",
			  width: "100%",
			  layoutMargin: 10,
			  membersMargin: 1,
			  members: [			                 
			                 isc.HLayout.create({
			                 defaultLayoutAlign: "center",
			                 align: "center",
			                 layoutMargin: 5,
			                 membersMargin: 10,
			                 members: [this.okButton, this.cancelButton]
			                 })
			            ]
		  })];
		this.Super('initWidget', arguments);
	}
});