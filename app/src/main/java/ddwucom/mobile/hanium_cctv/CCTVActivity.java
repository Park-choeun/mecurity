package ddwucom.mobile.hanium_cctv;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CCTVActivity extends AppCompatActivity {

    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅
    Intent intent = null;
    Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebView.loadUrl("http://175.122.21.45:81/?action=stream"); // 웹뷰에 표시할 라즈베리파이 주소, 웹뷰 시작
        //라즈베리파이_아이피주소:8090/?action=stream
    }

    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CCTVActivity.this);

        switch (v.getId()) {
            case R.id.btn1: //순찰요청
                builder.setTitle("순찰요청")
                        .setMessage("지금 당장 순찰을 요청하시겠습니까?")
                        .setNegativeButton("요청", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CCTVActivity.this, "가까운 경찰서로 순찰요청이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CCTVActivity.this, "순찰요청이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.btn2: //신고요청
                builder.setTitle("신고요청")
                        .setMessage("지금 당장 신고를 요청하시겠습니까?")
                        .setNegativeButton("신고", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CCTVActivity.this, "112로 즉시 전화연결.", Toast.LENGTH_SHORT).show();
                                mIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"));
                                startActivity(mIntent);
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CCTVActivity.this, "신고요청이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
        }
    }
}
