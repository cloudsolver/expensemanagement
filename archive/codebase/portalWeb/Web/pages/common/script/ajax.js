// AJAX Framework Version 1.0 
// Copyright 2005 Jason Graves (GodLikeMouse)
// This file is free to use and distribute under the GNU open source
// license so long as this header remains intact.
// For more information please visit http://www.godlikemouse.com
// To have changes incorporated into the AJAX Framework please contact godlikemouse@godlikemouse.com
// Supported Browsers: IE 6.0+, Mozilla based browsers
//
// Developer Note: .NET WebService Methods must be tagged with [SoapRpcMethod] for parameters to be passed. 


//AJAX main class
function AJAX(){

	var ajaxIndentity = 0;
	var nameSpace = "http://tempuri.org/";

	var getAJAXIdentity = function(){
	    return "AJAX" + (ajaxIndentity++);
    }//end GetAJAXIdentity()
    
    //Overridden toString method.
    this.toString = function(){
		return "AJAX Framework Class";
	}//end toString()
    
    //Method for error handling.
    this.onError = function(error){
		alert(error);
    };//end onError()

    //Call a page with a callback function name.
    this.callPage = function(url, callbackFunction){
	    var iframe = document.createElement("IFRAME");
	    var IE = (navigator.appName.indexOf("Microsoft") >= 0);
        iframe.id = getAJAXIdentity();
        
        if(IE){
			iframe.style.display = "none";
			document.appendChild(iframe);
		}
		else{
			iframe.style.display = "none";
			document.body.appendChild(iframe);
		}//end if
		
	    iframe.src = url;

	    if(IE){
			iframe.onreadystatechange = function(){
				if(this.readyState == "complete"){
					callbackFunction(document.frames[iframe.id].document.body.innerHTML);
					document.removeChild(iframe);
				}//end if
			};
	    }
	    else{
			iframe.addEventListener("load", function(){
				callbackFunction(document.getElementById(iframe.id).contentDocument.body.innerHTML);
				document.body.removeChild(document.getElementById(iframe.id));
			}, false);
	    }//end if
	};//end callPage()
	
	//Call a web service, pass any additional aruments in as "key=value"
	this.callService = function(serviceUrl, soapMethod, callbackFunction){
		var IE = (navigator.appName.indexOf("Microsoft") >= 0);
		
		var callServiceError = this.onError;
		
		if(IE){
			serviceUrl += "?WSDL";
			var soapEnvelope = new String();
			soapEnvelope += "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			soapEnvelope += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
			soapEnvelope += "<soap:Body>";
			soapEnvelope += "<" + soapMethod + " xmlns=\"" + nameSpace + "\">";
			
			if(arguments.length > 3){
				for (var i = 3; i < arguments.length; i++)
				{
					var params = arguments[i].split("=");
					soapEnvelope += "<" + params[0] + ">";
					soapEnvelope += params[1];
					soapEnvelope += "</" + params[0] + ">";
				}//end for
			}//end if
			
			soapEnvelope += "</" + soapMethod + ">";
			soapEnvelope += "</soap:Body>";
			soapEnvelope += "</soap:Envelope>";
			
			var xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");

			xmlHttp.Open("POST", serviceUrl);		
			xmlHttp.setRequestHeader("Content-Type", "text/xml");
			xmlHttp.setRequestHeader("SOAPAction", nameSpace + soapMethod);
			xmlHttp.Send(soapEnvelope);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					if(callbackFunction){
						var responseXml = new ActiveXObject('Microsoft.XMLDOM');
						responseXml.loadXML(xmlHttp.responseText);
						
						var responseNode = responseXml.selectSingleNode("//" + soapMethod + "Response");
						if(!responseNode)
							responseNode = responseXml.selectSingleNode("//" + soapMethod + "Result");
						if(!responseNode)
							callServiceError("Response/Result node not found.\n\nResponse:\n" + xmlHttp.responseText);
							
						var resultNode = responseNode.firstChild;
						if (resultNode != null){
							try{
								callbackFunction(resultNode.text);
							}
							catch(ex){
								callServiceError(ex);
							}//end tc
						}
						else{
							try{
								callbackFunction();
							}
							catch(ex){
								callServiceError(ex);
							}//end tc
						}//end if
					}//end if
				}//end if
			};
		}
		else{
			var soapCall = new SOAPCall();
			var soapParams = new Array();
			var headers = new Array();
			var soapVersion = 0;
			var object = nameSpace;
			
			if(serviceUrl.indexOf("http://") < 0)
				serviceUrl = document.location + serviceUrl;
				
			soapCall.transportURI = serviceUrl;
			soapCall.actionURI = nameSpace + soapMethod;
			
			for(var i=3; i<arguments.length; i++){
				var params = arguments[i].split("=");
				soapParams.push( new SOAPParameter(params[1],params[0]) );
			}//end for
			
			try{
				soapCall.encode(soapVersion, soapMethod, object, headers.length, headers, soapParams.length, soapParams);
			}
			catch(ex){
				serviceCallError(ex);
			}//end tc
			
			try{
				soapCall.asyncInvoke(
					function(resp,call,status){
						
						if(resp.fault)
							return callServiceError(resp.fault);
						if(!resp.body){
							callServiceError("Service " + call.transportURI + " not found.");
						}
						else{
							try{
								callbackFunction(resp.body.firstChild.firstChild.firstChild.data);
							}
							catch(ex){
								callServiceError(ex);
							}//end tc
						}//end if
					}
				);
			}
			catch(ex){
				serviceCallError(ex);
			}//end tc
			
		}//end if
		
	}//end callService()
	
	//Method for setting the namespace
	this.setNameSpace = function(ns){
		nameSpace = ns;
	}//end setNameSpace()
	
	//Method for returning the namespace
	this.getNameSpace = function(){
		return ns;
	}//end getNameSpace()
	
}//end AJAX()

