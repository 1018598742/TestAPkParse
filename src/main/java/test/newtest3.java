package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;




public class newtest3 {
    public static void main(String[] arg)
    {
        String apkUrl = "C:\\Users\\fta\\Desktop\\鼎桥jar和文档\\mobilestyletclient-release.apk";
        String apkUrl1 = "C:\\Users\\Administrator\\Desktop\\sign.apk";
        String result = getSingle(apkUrl);
//        String result2 = getSingle(apkUrl1);
        System.out.println(result+"~");
    }


    public static String getSingle(String apkUrl){
        Process p;
        //test.bat中的命令是ipconfig/all
        String cmd = "jarsigner -verify -verbose -certs " + apkUrl;
//        String cmd="jarsigner -verify -verbose -certs C:\\Users\\Administrator\\Desktop\\PandaClient.apk";
        String resultstr = null;
        try
        {
            //执行命令
            p = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream fis=p.getInputStream();
            //用一个读输出流类去读
            //用缓冲器读行
            BufferedReader br=new BufferedReader( new InputStreamReader(fis,"GB2312"));
            String line=null;
            //直到读完为止
            int i = 0;
            while((line=br.readLine())!=null)
            {
                if(line.contains("X.509")){  //解析符合自己需要的內容，获取之后，直接返回。
                	 System.out.println(line);
                    resultstr = line;
                    break;
                }
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return resultstr.replace("      X.509, ", "");
    }
}
