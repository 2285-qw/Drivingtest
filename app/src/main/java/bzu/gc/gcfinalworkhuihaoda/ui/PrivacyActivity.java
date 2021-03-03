package bzu.gc.gcfinalworkhuihaoda.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import bzu.gc.gcfinalworkhuihaoda.R;

/**
 * Time:         2021/2/27
 * Author:       C
 * Description:  PrivacyActivity
 * on:
 */
public class PrivacyActivity extends AppCompatActivity {

    final String news = "<font color='black' size='48'>云皓考驾照隐私政策</font><br>" +
            "<font color='black' size='24'><br>深圳市辉皓达实业发展有限公司</font><br>" +
            "<font  size='24'><br>&nbsp;&nbsp;尊敬的用户: 云皓考驾照应用APP程序（以下简称“云皓考驾照”）是一款由深圳市辉皓达实业发展限公司（以下统一简称“辉皓达实业”）所有的互联网学车软件；【特别提示】请您认真阅读我们的《隐私政策》。阅读过程中，如您有任何疑问，可及时与我们联系。如您不同意隐私政策中的任何条款，请您立即停止使用云皓考驾照 </font><br>"
            + "<font color='black' size='24'><br>1.云皓考驾照收集的信息</font><br>"
            + "<font size='24'><br>为了给您提供更好的云皓考驾照服务，在您使用云皓考驾照时平将台自动接收并记录的您的浏览器和计算机上的信息，包括但不限于您的IP地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据；  </font><br>"
            + "<font size='24' color='black'><br>2. 信息的存储</font><br>"
            + "<font size='24'><br>• 2.1 信息存储的地点 •云皓考驾照会按照法律法规规定，将境内收集的用户个人信息存储于中国境内。  </font><br>"
            + "<font size='24'><br>• 2.2 信息存储的期限 • 云皓考驾照会依照个人信息的不同等级存储不同期限，存储期限严格按照法律及相关法规规定，最低期限不少于6个月。  </font><br>"
            + "<font size='24' color='black'><br>3. 信息安全 </font><br>"
            + "<font size='24'><br>• 3.1 云皓考驾照努力为用户的信息安全提供保障，云皓考驾照将在合理的安全水平内使用各种安全保护措施以保障信息的安全。例如，云皓考驾照会使用加密技术来保护你的个人信息。  </font><br>"
            + "<font size='24'><br>• 3.2 云皓考驾照建立专门的管理制度、流程和组织以保障信息的安全。例如，云皓考驾照严格限制访问信息的人员范围，要求他们遵守保密义务。   </font><br>"
            + "<font color='black' size='24'><br>4.云皓考驾照如何使用信息   </font><br>"
            + "<font size='24'><br>• 4.1云皓考驾照收集的你的个人信息，将被用以向你提供正常服务、优化云皓考驾照的服务以及保障你的帐号安全、等。  </font><br>"
            + "<font size='24'><br>• 4.2 为了确保服务的安全，帮助驾考家园更好地了解云皓考驾照应用程序的运行情况，云皓考驾照可能记录相关信息，例如，你使用应用程序的频率、崩溃数据、总体使用情况、性能数据以及应用程序的来源。云皓考驾照不会将云皓考驾照存储在分析软件中的信息与你在应用程序中提供的任何个人身份信息相结合。  </font><br>"
            + "<font size='24'><br>• 4.3 如云皓考驾照使用你的个人信息，超出了与收集时所声称的目的及具有直接或合理关联的范围，云皓考驾照将在使用你的个人信息前，再次向你告知并征得你的明示同意。   </font><br>"
            + "<font color='black' size='24'><br>5.对外提供</font><br>"
            + "<font size='24'><br>• 5.1 目前，云皓考驾照不会主动共享或转让你的个人信息至第三方，下列情形除外：  </font><br>"
            + "<font size='24'><br>• 1）云皓考驾照已经取得你或你监护人的授权或同意；  </font><br>"
            + "<font size='24'><br>• 2）司法机关或行政机关基于法定程序要求云皓考驾照披露的；  </font><br>"
            + "<font size='24'><br>• 3）云皓考驾照为维护自身合法权益而向用户提起诉讼或仲裁时；   </font><br>"
            + "<font size='24'><br>• 4）根据你与云皓考驾照相关服务条款、应用许可使用协议的约定；   </font><br>"
            + "<font size='24'><br>• 5）在法律允许的范围内，为保障云皓考驾照、云皓考驾照用户以及社会公共利益免受损害时；   </font><br>"
            + "<font size='24'><br>• 6）符合你与其他第三人之间有关约定的；  </font><br>"
            + "<font size='24'><br>• 5.2 云皓考驾照不会对外公开披露其收集的个人信息，如必须公开披露时，云皓考驾照会向你告知此次公开披露的目的、披露信息的类型及可能涉及的敏感信息，并征得你的明示同意。  </font><br>"
            + "<font size='24'><br>• 5.3 随着我们业务的持续发展，我们有可能进行合并、收购、资产转让等交易，我们将告知你相关情形，按照法律法规及不低于本指引所要求的标准继续保护或要求新的控制者继续保护你的个人信息。 </font><br>"
            + "<font color='black' size='24'><br>6. 变更</font><br>"
            + "<font size='24'><br>为了给您提供更好的服务以及随着我们业务的发展，云皓考驾照可能会适时对本指引进行修订。当指引的条款发生变更时，云皓考驾照会在你登录及版本更新时以推送通知、弹窗的形式向你展示变更后的指引。 </font><br>"
            + "<font size='24'><br>7. 未成年人保护 \n" +
            "云皓考驾照非常重视对未成年人个人信息的保护。根据相关法律法规的规定，若你是18周岁以下的未成年人，在使用云皓考驾照服务前，应事先取得你的家长或法定监护人的书面同意。若你是未成年人的监护人，当你对你所监护的未成年人的个人信息有相关疑问时，请通过云皓考驾照公示的联系方式与云皓考驾照联系。</font><br>"
            + "<font size='24'><br> 8. 与云皓考驾照联系\n" +
            " 当你有其他的投诉、建议或相关问题时，请利用电子邮件3360917126@qq.com 联系我们，或者通过QQ ：3360917126联系我们，云皓考驾照将尽快审核所涉问题，并在验证你的用户身份后及时予以回复。 </font><br>";

    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        textView = findViewById(R.id.text);

        textView.setText(Html.fromHtml(news));//这是显示段落文本的,通过解析html
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());//段落文本的话要加这个
    }
}
