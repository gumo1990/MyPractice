package com.example.user.mydemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.example.user.mydemo.R;
import com.example.user.mydemo.views.MyWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whq on 2017/8/15.
 * 1.顶部加原生广告轮播图--compile 'com.bigkoo:convenientbanner:2.0.5'
 * 2.内容用h5交互--compile 'com.github.bumptech.glide:glide:4.0.0-RC0'
 * compile 'com.github.bumptech.glide:compiler:4.0.0-RC0'
 * 3.设置图片适配--compile 'org.jsoup:jsoup:1.10.1'
 */

public class FourActivity extends Activity implements View.OnClickListener {

    private List<String> imageList = new ArrayList<>();
    private String datas = "逸佳空气净化凝露<br />\r\n<br />\r\n商品品牌：逸佳<br />\r\n商品规格：185g/盒<br />" +
            "\r\n商品重量：1.6kg<br />\r\n<p>\r\n\t商品产地：天津\r\n</p>\r\n<p>\r\n\t<img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_59/201704241430599930.jpg\" alt=\"1.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_59/201704241430592080.jpg\" alt=\"2.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_60/201704241431005104.jpg\" alt=\"3.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_61/201704241431011882.jpg\" alt=\"4.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_61/201704241431011456.jpg\" alt=\"5.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_62/201704241431025098.jpg\" alt=\"6.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_62/201704241431026561.jpg\" alt=\"7.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_63/201704241431036995.jpg\" alt=\"8.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_65/201704241431054589.jpg\" alt=\"9.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_65/201704241431056676.jpg\" alt=\"10.jpg\" /><img src=\"http://www.svgouwu." +
            "com/data/files/store_1582/goods_90/201707191451301092.jpg\" alt=\"201704241431066955.jpg\" /><img " +
            "src=\"http://www.svgouwu.com/data/files/store_1582/goods_67/201704241431071768.jpg\" alt=\"12.jpg\" />" +
            "<img src=\"http://www.svgouwu.com/data/files/store_1582/goods_67/201704241431074423.jpg\" alt=\"13.jpg\" /> \r\n</p>";

    @BindView(R.id.btn_top_back)
    TextView tv_four_back;

    @BindView(R.id.tv_top_title)
    TextView tv_four_title;

    @BindView(R.id.tv_top_right)
    TextView tv_four_next;

    @BindView(R.id.cb_four_webview)
    ConvenientBanner cb_four_webview;//头图

    @BindView(R.id.myweb_fours)
    MyWebView myweb_four;//web

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        ButterKnife.bind(this);
        imageList.add("http://www.svgouwu.com/data/files/store_1582/goods_43/small_201704241430431877.jpg");
        imageList.add("http://www.svgouwu.com/data/files/store_1582/goods_46/small_201704241430465175.JPG");
        imageList.add("http://www.svgouwu.com/data/files/store_1582/goods_48/small_201704241430489871.JPG");
        initView();
        initData();
        registListener();

    }

    private void initView() {

    }

    private void initData() {
        tv_four_title.setText("FourActivity");

        cb_four_webview.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new FourActivity.LocalImageHolderView();
            }
        }, imageList).setPageIndicator(new int[]{R.drawable.web_circular_selected_focus, R.drawable.web_circular_selected});

        myweb_four.loadDataWithBaseURL(null, getNetContent(), "text/html", "utf-8", null);
    }

    @OnClick({R.id.btn_top_back, R.id.tv_top_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top_back:
                finish();
                break;
            case R.id.tv_top_right:
                startActivity(new Intent(this, FiveActivity.class));
                break;
            default:
                break;
        }
    }

    private class LocalImageHolderView implements Holder<String> {

        private ImageView image;

        @Override
        public View createView(Context context) {
            image = new ImageView(context);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            return image;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(FourActivity.this).load(data).into(image);
        }
    }

    private void registListener() {
        cb_four_webview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(FourActivity.this, "第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 适配h5图片大小
     */
    private String getNetContent() {
        Document doc = Jsoup.parse(datas);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

}
