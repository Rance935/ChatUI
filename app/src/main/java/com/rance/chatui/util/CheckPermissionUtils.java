package com.rance.chatui.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

/**
 * 作者：Rance on 2016/12/14 11:09
 * 邮箱：rance935@163.com
 */
public class CheckPermissionUtils {
    // 音频获取源
    public static int audioSource = MediaRecorder.AudioSource.MIC;
    // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    public static int sampleRateInHz = 44100;
    // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
    public static int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
    public static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    // 缓冲区字节大小
    public static int bufferSizeInBytes = 0;

    /**
     * 判断是是否有录音权限
     */
    public static boolean isHasPermission(final Context context){
        bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord =  new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try{
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        /**
         * 根据开始录音判断是否有录音权限
         */
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("经检测语音权限未开启，设置方法：三方手机管理(应用宝、360)->安全->权限管理程序->应用程序->勾选语音权限。或去设置中心找到应用设置权限")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            context.startActivity(new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
                        }
                    })
                    .show();
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;
        return true;
    }
}
