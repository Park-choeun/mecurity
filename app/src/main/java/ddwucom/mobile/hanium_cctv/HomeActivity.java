package ddwucom.mobile.hanium_cctv;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Intent intent = null;
    Intent mIntent = null;
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this, R.raw.alert1, 1);

    }

    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        switch (v.getId()) {
            case R.id.home1: //CCTV확인
                intent = new Intent(this, CCTVActivity.class);
                startActivity(intent);
                break;
            case R.id.home2: //경보음 재생
                soundPool.play(sound, 1, 1, 0, 0, 1);
                break;
            case R.id.home3: //신고요청
                builder.setTitle("신고요청")
                        .setMessage("지금 당장 신고를 요청하시겠습니까?")
                        .setNegativeButton("신고", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "112로 즉시 전화연결.", Toast.LENGTH_SHORT).show();
                                mIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"));
                                startActivity(mIntent);
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "신고요청이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.home4: //인근경찰서찾기
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}

