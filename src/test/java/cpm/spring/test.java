package cpm.spring;

import com.haier.utils.XmlUtil;

public class test {
	
	public static void main(String[] args) throws  Exception{

		String xml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><returnstatus>Faild</returnstatus><message>message</message><remainpoint></remainpoint><taskID>taskID</taskID><successCounts>successCounts</successCounts></returnsms>";

		String res=XmlUtil.getValueByNameXml(xml,"returnstatus");
		System.out.println(res);
		
	}

	
	public Object getTest(){
		
		Object obj=new Object();
		obj=null;
		if(obj==null){
			throw new RuntimeException("fgjhkjhgdsafghjhgf");
		}
		return "1";
	}
}

