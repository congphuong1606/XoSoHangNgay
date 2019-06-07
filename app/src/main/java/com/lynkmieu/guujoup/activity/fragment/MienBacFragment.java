package com.lynkmieu.guujoup.activity.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.activity.SXKQActivity;
import com.lynkmieu.guujoup.webview.MyWebViewClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MienBacFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MienBacFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MienBacFragment extends Fragment {
    public static final String MY_URL = "https://xoso.me/embedded/kq-mienbac?ngay_quay=";
    private WebView webView;
    String script = "function setNone(){var html=document.getElementsByTagName('html').item(0);if(html!==null){html.style.pointerEvents='none'}var embeded=document.getElementsByClassName('embeded-breadcrumb').item(0);if(embeded!==null){embeded.style.display='none'}var panel=document.getElementsByClassName('control-panel').item(0);if(panel!==null){panel.style.display='none'}var conect_out=document.getElementsByClassName('conect_out').item(0);if(conect_out!==null){conect_out.style.display='none'}var title=document.getElementsByClassName('title-bor').item(0);if(title!==null){title.style.display='none'}};setNone();";


    private Button btnkiyo;
    private Button btnkino;
    private Button btnasita;

    private String kiyo;
    private String kino;
    private String asita;
    private String ASITA;
    private RelativeLayout progressbar;
    public MienBacFragment() {

    }

    public static MienBacFragment newInstance() {
        MienBacFragment fragment = new MienBacFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mien_bac, container, false);
        webView =(WebView)view.findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnTouchListener(null);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView = (WebView) view.findViewById(R.id.webView);
        btnkino = (Button) view.findViewById(R.id.kinoday);
        btnkiyo = (Button) view.findViewById(R.id.kiyoday);
        btnasita = (Button) view.findViewById(R.id.asitaday);
        progressbar = (RelativeLayout) view.findViewById(R.id.progressber);
        progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Chờ một xíu",Toast.LENGTH_LONG).show();
            }
        });
        setDaytoView();
        setClick();
        ASITA = creatday(86400000, toMillis(kiyo));
        goURl();
        return view;
    }

    public void goURl() {
        progressbar.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                webView.loadUrl("javascript:" + script);
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });
        webView.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void onload() {

                // This is executed on a background thread, so post back to UI thread.
                webView.post(new Runnable() {

                    @Override
                    public void run() {
                        //  webView.loadUrl("javascript:function setNone(){var d=document.getElementsByTagName('html').item(0);if(d!==null){d.style.pointerEvents='none'}};setNone();");
                        webView.loadUrl("javascript:" + script);
                    }

                });

            }

        }, "android");
        webView.loadUrl(MY_URL + kiyo.split(" ")[0]);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setClick() {
        btnkino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kiyo = creatday(-86400000, toMillis(kino) + 86400000);
                kino = creatday(-86400000, toMillis(kiyo));
                asita = creatday(86400000, toMillis(kiyo));
                btnkiyo.setText(kiyo.split(" ")[0]);
                btnasita.setText(asita.split(" ")[0]);
                btnkino.setText(kino.split(" ")[0]);
                goURl();


            }
        });
        btnasita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!asita.split(" ")[0].equals(ASITA.split(" ")[0])) {

                    kiyo = creatday(+86400000, toMillis(asita) - 86400000);
                    kino = creatday(-86400000, toMillis(kiyo));
                    asita = creatday(86400000, toMillis(kiyo));
                    btnkiyo.setText(kiyo.split(" ")[0]);
                    btnasita.setText(asita.split(" ")[0]);
                    btnkino.setText(kino.split(" ")[0]);
                    goURl();
                }


            }
        });

    }

    private void setDaytoView() {
        kiyo = creatday(0, System.currentTimeMillis());
        kino = creatday(-86400000, toMillis(kiyo));
        asita = creatday(86400000, toMillis(kiyo));
        btnkiyo.setText(kiyo.split(" ")[0]);
        btnasita.setText(asita.split(" ")[0]);
        btnkino.setText(kino.split(" ")[0]);
    }

    public long toMillis(String myDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HHmmss");
        Date date = null;
        try {
            date = dateFormat.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        return millis;
    }

    public static String creatday(long durition, long time) {
        //1ngay =(1000 * 60 * 60 * 24)
        Date mydate = new Date(time + durition);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HHmmss");
        String day = dateFormat.format(mydate);
        int h = Integer.parseInt(day.split(" ")[1]);
        if (durition == 0 && h < 181500) {
            day = creatday(-86400000, System.currentTimeMillis());
        }
        return day;
    }

}
