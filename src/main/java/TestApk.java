import utils.ApkUtils;

public class TestApk {

    public static void main(String[] args) {
        ApkUtils apkUtils = new ApkUtils();
//        apkUtils.getSha1("C:\\Users\\fta\\Desktop\\app-debug.apk");
//        apkUtils.getSha1("C:\\Users\\fta\\Desktop\\鼎桥jar和文档\\mobilestyletclient-release.apk");
        String sha1 = apkUtils.getSha1("C:\\Users\\fta\\Desktop\\mytemp\\alipay_wap_main.apk");
        System.out.println("签名是：" + sha1);
    }
}