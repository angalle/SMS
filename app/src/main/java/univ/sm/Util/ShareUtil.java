package univ.sm.Util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.SocialObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PE_LHS on 2018-01-18.
 */

public class ShareUtil {
    public static void shareStaticKakao(Context context) {
        String templateId = "13377";

        Map<String, String> templateArgs = new HashMap<String, String>();
        //templateArgs.put("template_arg1", "value1");
        //templateArgs.put("template_arg2", "value2");

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        //serverCallbackArgs.put("user_id", "${current_user_id}");
        //serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendCustom(context, templateId, templateArgs, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                Toast.makeText(context,"공유되었습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void shareFacebook(Activity activity) {
        SharePhoto photo = new SharePhoto.Builder()
                .setImageUrl(Uri.parse("https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw"))
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=univ.sm"))
                .build();

        ShareDialog shareDialog = new ShareDialog(activity);
        shareDialog.show(content, ShareDialog.Mode.FEED);
    }
    public static void shareDynamicKakao(Context context) {
        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder("SMS(선문셔틀 버스 앱 + 콜벤)",
                        "https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=w720-h310-rw",
                        LinkObject.newBuilder().setWebUrl("https://play.google.com/store/apps/details?id=univ.sm")
                                .setMobileWebUrl("https://play.google.com/store/apps/details?id=univ.sm").build())
                        .setDescrption("클릭시 앱 다운로드 사이트로 이동!")
                        .build())
                /*.setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                        .setSharedCount(30).setViewCount(40).build())*/
                .addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder().setWebUrl("https://play.google.com/store/apps/details?id=univ.sm").setMobileWebUrl("https://play.google.com/store/apps/details?id=univ.sm").build()))
                /*.addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl("https://play.google.com/store/apps/details?id=univ.sm")
                        .setMobileWebUrl("https://play.google.com/store/apps/details?id=univ.sm")
                        //.setAndroidExecutionParams("key1=value1")
                        //.setIosExecutionParams("key1=value1")
                        .build()))*/
                .build();

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        //serverCallbackArgs.put("user_id", "${current_user_id}");
        //serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendDefault(context, params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                Toast.makeText(context,"공유되었습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
