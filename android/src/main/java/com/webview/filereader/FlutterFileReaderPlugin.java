package com.webview.filereader;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.HashMap;

import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.engine.plugins.FlutterPlugin;


public class FlutterFileReaderPlugin implements FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler {

    private static String TAG = "FileReader";

    public static final String channelName = "wv.io/FileReader";
    private FlutterPluginBinding flutterPluginBinding;

    private Context ctx;
    private Context activityContext;
    private MethodChannel methodChannel;

    private Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    public FlutterFileReaderPlugin() {
        super();
    }

    private void loadParameters(FlutterPluginBinding binding) {
        ctx = binding.getApplicationContext();
        methodChannel = new MethodChannel(binding.getBinaryMessenger(), channelName);
        methodChannel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        result.notImplemented();
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        this.flutterPluginBinding = binding;
        Log.d(TAG, "onAttachedToEngine");
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
        Log.d(TAG, "onDetachedFromEngine");
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        this.activityContext = binding.getActivity();
        this.loadParameters(this.flutterPluginBinding);
        Log.d(TAG, "onAttachedToActivity");
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        Log.d(TAG, "onDetachedFromActivityForConfigChanges");
    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Log.d(TAG, "onReattachedToActivityForConfigChanges");
    }

    @Override
    public void onDetachedFromActivity() {
        Log.d(TAG, "onDetachedFromActivity");
        activityContext = null;
    }
}
