isc.defineClass('PopUp_Param',isc.OBPopup);

isc.PopUp_Param.addProperties({
	width: 320,
	  height: 200,
	  title: "Generate KPI Measurement",
	  showMinimizeButton: false,
	  showMaximizeButton: false,
	  initWidget: function(){
		  var me=this;
		    // Form that contains the parameters
		    this.mainform = isc.DynamicForm.create({
		      fields: [{
			    name: 'pegawai',
		        title: 'Pegawai',
			    height: 20,
			    width: 200,
			    type: '_id_19' //List reference,
			    ,valueMap:this.Data.dataEmployee.getValueMap('idPegawai','pegawai')
			  },{
				name: 'Tahun',
			    title: 'Tahun',
				height: 20,
				width: 200,
				type: '_id_19' //List reference
			    ,valueMap:this.Data.periode.getValueMap('idTahun','namaTahun')
				},{
				name: 'Bulan',
				title: 'Bulan',
				height: 20,
				width: 200,
				type: '_id_17' //List reference
				,valueMap:this.Data.periode.getValueMap('periodID','namaBulan')
			}],wrapItemTitles:false
		   });
			
		    // OK Button
		    this.okButton = isc.OBFormButton.create({
		      title: ('Submit'),
		      popup: this,
		      click: function () {
		        var callback, i;

		        callback= function(rpcResponse, data, rpcRequest){
		        	var View=rpcRequest.clientContext.originalView.getView(rpcResponse.clientContext.originalView.view.tabId);
		        	View.messageBar.setMessage(data.type, data.title, data.message);
		        	me.closeClick();
		        	rpcRequest.clientContext.originalView.refresh(false, false);
				};

				OB.RemoteCallManager.call('org.wirabumi.hris.employee.master.event.DataGenerateMeasurement', {
				      idJtKPI:me.id,
				      idPegawai:me.mainform.getItem('pegawai').getValue(),
				      year:me.mainform.getItem('Tahun').getValue(),
				      month:me.mainform.getItem('Bulan').getValue(),
				      command:'setData'
				}, {}, callback,{originalView: me.view});
				
		      }
		   });
		   
		   // Cancel Button
		   this.cancelButton = isc.OBFormButton.create({
		     title:('Cancel'),
		     popup: this,
		     action: function () {
		       this.popup.closeClick();
		     }
		   }); 
		   
		   //Add the elements into a layout   
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
		                 align: "left",
		                 layoutMargin: 1,
		                 membersMargin: 6,
		                 members: this.mainform
		                 }),
		                 isc.HLayout.create({
		                 defaultLayoutAlign: "center",
		                 align: "center",
		                 layoutMargin: 5,
		                 membersMargin: 10,
		                 members: [this.okButton, this.cancelButton]
		                 })
		                 ]
		     })
		   ];
		    this.Super('initWidget', arguments);
	  }
});