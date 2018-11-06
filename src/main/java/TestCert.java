import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkSigner;
import net.dongliu.apk.parser.bean.ApkV2Signer;
import net.dongliu.apk.parser.bean.CertificateMeta;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;

public class TestCert {

    public static void main(String[] args) {
        TestCert testCert = new TestCert();
        testCert.mySinger();
    }

    private void mySinger() {
        String filePath = "C:\\Users\\fta\\Desktop\\鼎桥jar和文档\\mobilestyletclient-release.apk";
        try (ApkFile apkFile = new ApkFile(new File(filePath))) {
            List<ApkSigner> signers = apkFile.getApkSingers(); // apk v1 signers
            if (signers != null && signers.size() > 0) {
                for (ApkSigner apkSigner : signers) {
                    List<CertificateMeta> certificateMetas = apkSigner.getCertificateMetas();
                    for (CertificateMeta certificateMeta : certificateMetas) {
                        String certMd5 = certificateMeta.getCertMd5();
                        System.out.println(certMd5);
                        String certBase64Md5 = certificateMeta.getCertBase64Md5();
                        System.out.println("certBase64Md5=" + certBase64Md5);
                        String signAlgorithm = certificateMeta.getSignAlgorithm();
                        System.out.println("signAlgorithm=" + signAlgorithm);

                    }
                    System.out.println("信息：" + apkSigner.getPath());
                }
            } else {
                System.out.println("== null? " + signers.size());
            }
//            List<ApkV2Signer> v2signers = apkFile.getApkV2Singers(); // apk v2 signers
//            if (v2signers != null && v2signers.size() > 0) {
//                for (ApkV2Signer apkV2Signer : v2signers) {
//                    System.out.println("2信息：" + apkV2Signer.getCertificateMetas().size());
//                }
//            } else {
//                System.out.println("==2 null? " + v2signers.size());
//            }
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
