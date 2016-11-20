
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
   /* 工具：
    * 生成XXXX-yy到XXXX-yy的sql语句
    * by qy*/

public class insert {

	public static void main(String[] args) throws IOException {

		List<String> temp=temp(2030,2038);
		List<String> result=cut("203002","203801",temp);
		File file=new File("d://ACCT_ITEM_TOTAL.txt");
		if(!file.exists())
		{
			file.createNewFile();
		}
        FileWriter fw=new FileWriter(file);

		for(String o:result)
		{
			//System.out.println(o);
			StringBuffer sb=new StringBuffer();
			sb.append("CREATE TABLE ACCT_ITEM_TOTAL");
			sb.append(o);
			sb.append("(col_0 VARCHAR2(50) , col_1 VARCHAR2(50) , col_2 VARCHAR2(50) , col_3 VARCHAR2(50) , col_4 VARCHAR2(50) , col_5 VARCHAR2(50) , col_6 VARCHAR2(50) , col_7 VARCHAR2(50) , col_8 VARCHAR2(50) ) TABLESPACE TBS_ACT_DATA;");
			fw.write(sb.toString()+"\r\n");
			fw.flush();
			//System.out.println(sb);
		}
		//Index
		for(String o:result)
		{//CREATE INDEX  indx_7_ACCT_ITEM_TOTAL_TOTAL_TOTAL_TOTAL_TOTAL202601 ON  ACCT_ITEM_TOTAL_TOTAL_TOTAL_TOTAL_TOTAL202601(col_7) TABLESPACE TBS_ACT_INDX ;	
			for(int c=0;c<=8;c++)
			{	StringBuffer col=new StringBuffer();
				col.append("CREATE INDEX  indx_");
				col.append(c);
				col.append("_ACCT_ITEM_TOTAL"+o);
				col.append(" ON ACCT_ITEM_TOTAL"+o);
				col.append("(col_"+c+") ");
				col.append("TABLESPACE TBS_ACT_INDX;");
				fw.write(col.toString()+"\r\n");
				fw.flush();
				//System.out.println(col);
			}
		}
		fw.close();
	}

	public static List<String> temp(int start,int end)
	{   List<String> result=new ArrayList<String>();
	    int count=start;
     	while(count<=end)
	  {	String mon="";
	String yearmonth="";
	for(int i=1;i<=12;i++)
	{
		if(i<10)
		{
			mon="0"+i;
		}
		else
		{
			mon=String.valueOf(i);
		}
		yearmonth=count+mon;
		result.add(yearmonth);
		//System.out.println(yearmonth);
	}
	count++;
	}
	return result;
	}

	public static List<String> cut(String startmonth,String endmonth,List<String> temp)
	{
		List<String> result=new ArrayList<String>();
		int startindex=temp.indexOf(startmonth);
		int endindex=temp.indexOf(endmonth);
		for(int i=startindex;i<=endindex;i++)
		{
			String cell=temp.get(i);
			result.add(cell);
		}
		return result;
	}
}
