function submitForm(formid,cmd)
{

	var f=document.getElementById(formid);
	var cmdTag=document.getElementById('cmd');
	cmdTag.value=cmd;
	f.submit();
}

function toggle(it)
{
	var help=document.getElementById(it);
	if(help.style.display=="block")
	{
		hide(it);
	}
	else
	{
		show(it);
	}
}

function show(it)
{
var help=document.getElementById(it);
help.style.display="block";
}
function hide(it)
{
var help=document.getElementById(it);
help.style.display="none";
}

function doCommand(cmd,action,id)
{
var u=action+".do?cmd="+cmd+"&id="+id;
location.href=u;
}
function disableButton (button) {
  if (document.all || document.getElementById)
    button.disabled = true;
  else if (button) {
    button.oldOnClick = button.onclick;
    button.onclick = null;
    button.oldValue = button.value;
    button.value = 'DISABLED';
  }
}