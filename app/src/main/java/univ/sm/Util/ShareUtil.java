package univ.sm.Util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;


/**
 * Created by PE_LHS on 2018-01-18.
 */

public class ShareUtil {
    public static void shareKakao(Context context) {
        try {
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(context);
            final KakaoTalkLinkMessageBuilder kakaoMsgBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoMsgBuilder.addText("SMS - 선문대셔틀버스 시간표 \n콜벤합승기능이 추가되었어요 ~ \n처음한번 입력하면 입력정보는 계속 기억되요 ~ \n 많은홍보 해주세요 ~_~");

            String url = "https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw";
            String url2 = "https://lh3.googleusercontent.com/HWQPZPUdMvPpP_R5QQmxQiFgtFzfrlpV9mFHeQb56uhzgahxqUNHoGO_D00vsf3ACqA=h900-rw";
            String url3 = "https://lh3.googleusercontent.com/GIVzb9_SRj_JUQmL9vvZale6SaFQHsmfdswtRJ-0bZc3BwfYHYwIu3gUGryPHWMw8PnM=h900-rw";
            kakaoMsgBuilder.addImage(url, 160, 160);
            //kakaoMsgBuilder.addImage(url2,160,160);
            //kakaoMsgBuilder.addImage(url3,160,160);
            //kakaoMsgBuilder.addAppLink("https://play.google.com/store/apps/details?id=univ.sm");
            kakaoMsgBuilder.addAppButton("SMS - 선문대셔틀버스 시간표 & 콜벤합승");

            kakaoLink.sendMessage(kakaoMsgBuilder, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shareFacebook(Activity activity) {
        ShareLinkContent content = new ShareLinkContent.Builder()

                .setContentTitle("SMS - 선문대셔틀버스 시간표 & 콜벤합승")
                .setImageUrl(Uri.parse("https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw"))
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=univ.sm"))
                .setContentDescription("SMS - 선문대셔틀버스 시간표 \n콜벤합승기능이 추가되었어요 ~ \n처음한번 입력하면 입력정보는 계속 기억되요 ~ \n 많은홍보 해주세요 ~_~")
                .build();

        ShareDialog shareDialog = new ShareDialog(activity);
        shareDialog.show(content, ShareDialog.Mode.FEED);
    }
}
