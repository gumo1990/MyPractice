package Fragments;

import android.app.ListFragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.example.user.mydemo.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import Utils.Quake;

/**
 * Created by whq on 17/6/26 0026.
 * 用于显示地震列表
 */

public class EarthquakeListFragment extends ListFragment {

    ArrayAdapter<Quake> aa;
    ArrayList<Quake> earthquake = new ArrayList<>();
    private static final String TAG = "EARTHQUAKE";
    private Handler handler = new Handler();

    /**
     * 重写onActivityCreated方法，存储一个Quake对象的ArrayList，并
     * 使用一个ArrayAdapter将这个数组绑定到底层的ListView
     */

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int layoutID = R.layout.support_simple_spinner_dropdown_item;
        aa = new ArrayAdapter<Quake>(getActivity(), layoutID, earthquake);
        setListAdapter(aa);

        new Thread(new Runnable() {
            @Override
            public void run() {
                refreshEarthquakes();
            }
        }).start();
    }

    public void refreshEarthquakes() {
        //获得XML
        URL url;
        try {
            String quakeFeed = getString(R.string.quake_feed);
            url = new URL(quakeFeed);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {//访问成功，返回200
                InputStream in = httpURLConnection.getInputStream();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                //分析地震源
                Document dom = db.parse(in);
                Element docEle = dom.getDocumentElement();
                //清除旧的地震数据
                earthquake.clear();
                //获取每个地震相的列表
                NodeList nl = docEle.getElementsByTagName("entry");
                if (nl != null && nl.getLength() > 0) {
                    for (int i = 0; i < nl.getLength(); i++) {
                        Element entry = (Element) nl.item(i);
                        Element title = (Element) entry.getElementsByTagName("title").item(0);
                        Element g = (Element) entry.getElementsByTagName("georss:point").item(0);
                        Element when = (Element) entry.getElementsByTagName("updated").item(0);
                        Element link = (Element) entry.getElementsByTagName("link").item(0);

                        String details = title.getFirstChild().getNodeValue();
                        String hosename = "http://earhtquake.usgs.gov";
                        String linkString = hosename + link.getAttribute("href");

                        String point = g.getFirstChild().getNodeValue();
                        String dt = when.getFirstChild().getNodeValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
                        Date qdate = new GregorianCalendar(0, 0, 0).getTime();

                        qdate = sdf.parse(dt);

                        String[] location = point.split(" ");
                        Location l = new Location("dummyGPS");
                        l.setLatitude(Double.parseDouble(location[0]));
                        l.setLongitude(Double.parseDouble(location[1]));

                        String magnitudeString = details.split(" ")[1];
                        int end = magnitudeString.length() - 1;
                        double magnitude = Double.parseDouble(magnitudeString.substring(0, end));
                        details = details.split(",")[1].trim();

                        final Quake quake = new Quake(qdate, details, l, magnitude, linkString);
                        //处理一个新发现的地震
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                addNewQuake(quake);
                            }
                        });

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewQuake(Quake quake) {
        //将地震添加到数组列表中
        earthquake.add(quake);
        //aa更新数据ui
        aa.notifyDataSetChanged();
    }
}
