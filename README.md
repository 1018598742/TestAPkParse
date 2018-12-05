# Java 获取 apk 中的 SHA1 值 
可以获取 apk 的 sha1 值，主要是服务端使用（当上传apk后获取其 sha1 值）

之前的方法发现支付宝 apk 无法解压完全，所有换了方法
```
使用方法：
 ApkUtils apkUtils = new ApkUtils();
 String sha1 = apkUtils.getSha1Other("apk绝对路径");
```

#### 注意：
有的 apk 的 RSA 文件名并不是 CERT.RSA(使用爱加密加固后的apk为APP_KEYS.RSA)

## 获取应用包名

- 位置 aboutapk 包下
