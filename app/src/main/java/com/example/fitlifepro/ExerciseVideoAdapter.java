package com.example.fitlifepro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ExerciseVideoAdapter extends BaseAdapter
{
    List<Video> AdapterVideos;
    int SelectedInd = -1;

    // Constructor
    public ExerciseVideoAdapter(List<Video> adapterVideos, int selectedInd) {
        AdapterVideos = adapterVideos;
        SelectedInd = selectedInd;
    }

    // Getter & Setter for AdapterVideos
    public List<Video> getAdapterVideos() { return AdapterVideos; }

    public void setAdapterVideos(List<Video> adapterVideos) {
        AdapterVideos = adapterVideos;
        notifyDataSetChanged();
    }

    // Getter and Setter for SelectedInd
    public int getSelectedInd() { return SelectedInd; }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return AdapterVideos.size();
    }

    @Override
    public Object getItem(int i) {
        return AdapterVideos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Setting view = layout_exdemo_webview.xml
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.layout_exdemo_webview, viewGroup, false);
        }

        // Finding TextView inside layout_exdemo_webview.xml
        TextView txtViewExDemoWebView = view.findViewById(R.id.txtViewExDemoWebView);
        // Dynamically assigning VideoName
        txtViewExDemoWebView.setText(AdapterVideos.get(i).getVideoName());

        // Finding WebView inside layout_exdemo_webview.xml
        WebView webViewExDemo = view.findViewById(R.id.webViewExDemo);
        // Dynamically assigning VideoUrl
        String selectedURL = AdapterVideos.get(i).getVideoUrl();
        // Loading YouTube video
        webViewExDemo.loadData(selectedURL, "text/html", "utf-8");
        webViewExDemo.getSettings().setJavaScriptEnabled(true);
        webViewExDemo.setWebChromeClient(new WebChromeClient());

        return view;
    }
}
