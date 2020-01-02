import utils.ApkUtils;

public class TestApk {

    public static void main(String[] args) {
        ApkUtils apkUtils = new ApkUtils();
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\alipay_wap_main.apk");
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\app-debug.apk");
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\mobileqq_android.apk");
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\appsearch_AndroidPhone_1012271b.apk");
//        String sha1 = apkUtils.getSha1Other("C:\\Users\\fta\\Desktop\\mytemp\\mobilestylet_release_V1.0_unsign_signed.apk");
        String sha1 = apkUtils.getSha1Other("D:\\tempAPk\\alipay_wap_main.apk");
        System.out.println("签名是：" + sha1);
    }
}
