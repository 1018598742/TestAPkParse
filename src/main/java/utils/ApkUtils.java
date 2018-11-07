package utils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 使用指令读取到 apk 文件中的 sha1 值
 */
public class ApkUtils {

    private String cmdInfo = "keytool -printcert -file ";

    public String getSha1(String apkUrl) {
        String apkSHA1 = "";
        File apkFile = new File(apkUrl);
        if (apkFile.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(apkFile);
                ZipInputStream Zin = new ZipInputStream(fileInputStream);//输入源zip路径
                BufferedInputStream Bin = new BufferedInputStream(Zin);
                String Parent = "E:\\IntellijIdeaWorkplace\\Test\\ApkCert\\temp"; //输出路径（文件夹目录）
                File Fout = null;
                ZipEntry entry;
                while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
                    Fout = new File(Parent, entry.getName());
                    if (!Fout.exists()) {
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(Fout);
                    BufferedOutputStream Bout = new BufferedOutputStream(out);
                    int b;
                    while ((b = Bin.read()) != -1) {
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(Fout + "解压成功");
                }
                Bin.close();
                Zin.close();
                String filePath = Parent + "\\META-INF\\CERT.RSA";
                File signerFile = new File(filePath);
                if (signerFile.exists()) {
                    //执行命令
                    Process process = Runtime.getRuntime().exec(cmdInfo + filePath);
                    //取得命令结果的输出流
                    InputStream inputStream = process.getInputStream();
                    //用一个读输出流类去读
                    //用缓冲器读行
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
                    String line = null;
                    //直到读完为止
                    int i = 0;
                    while ((line = br.readLine()) != null) {
//                    System.out.println("每一行的数据：" + line);
                        if (line.contains("SHA1:")) {
                            apkSHA1 = line.substring("SHA1: ".length() + 1);
                            break;
                        }
                    }
                } else {
                    System.out.println("要解析的文件不存在");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apkSHA1;
    }

    /**
     * @param apkPath apk的绝对路径
     * @return
     */
    public String getSha1Other(String apkPath) {
        String apkSHA1 = "";
        File apkFile = new File(apkPath);
        if (apkFile.exists()) {
            try {
                ZipFile zipFile = new ZipFile(apkFile);
                Enumeration<? extends ZipEntry> files = zipFile.entries();
                ZipEntry entry = null;
                String outFilePath = ".//temp//CERT.RSA";
                File outFile = null;
                InputStream zipFileInputStream;
                OutputStream outputStream;
                while (files.hasMoreElements()) {
                    entry = files.nextElement();
                    String entryName = entry.getName();
                    if (!entryName.contains("CERT.RSA")) {
                        continue;
                    }
                    System.out.println(entryName);
                    zipFileInputStream = zipFile.getInputStream(entry);
                    outFile = new File(outFilePath);
                    if (!outFile.exists()) {
                        new File(outFile.getParent()).mkdirs();
                    }
                    System.out.println("绝对路径：" + outFile.getAbsolutePath());
                    outputStream = new FileOutputStream(outFile);
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    while ((len = zipFileInputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                    }
                    zipFileInputStream.close();
                    outputStream.flush();
                    outputStream.close();

                    apkSHA1 = executeCmd(outFile.getAbsolutePath());

                    break;
                }

                //删除临时文件
                if (outFile != null && outFile.exists()) {
                    if (outFile.isFile()) {
                        outFile.delete();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apkSHA1;
    }

    private String executeCmd(String filePath) throws IOException {
        String apkSHA1 = "";
        File signerFile = new File(filePath);
        if (signerFile.exists()) {
            //执行命令
            Process process = Runtime.getRuntime().exec(cmdInfo + filePath);
            //取得命令结果的输出流
            InputStream inputStream = process.getInputStream();
            //用一个读输出流类去读
            //用缓冲器读行
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
            String line = null;
            //直到读完为止
            int i = 0;
            while ((line = br.readLine()) != null) {
//                    System.out.println("每一行的数据：" + line);
                if (line.contains("SHA1:")) {
                    apkSHA1 = line.substring("SHA1: ".length() + 1);
                    break;
                }
            }
        } else {
            System.out.println("要解析的文件不存在");
        }
        return apkSHA1;
    }
}
