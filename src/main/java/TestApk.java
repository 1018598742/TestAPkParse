import utils.ApkUtils;

public class TestApk {

    public static void main(String[] args) {
        ApkUtils apkUtils = new ApkUtils();
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\alipay_wap_main.apk");
        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\app-debug.apk");
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\mobileqq_android.apk");
        System.out.println("签名是：" + sha1);
    }
}
