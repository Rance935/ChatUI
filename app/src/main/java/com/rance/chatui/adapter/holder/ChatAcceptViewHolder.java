package com.rance.chatui.adapter.holder;

import android.os.Handler;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rance.chatui.R;
import com.rance.chatui.adapter.ChatAdapter;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.util.EmotionUtils;
import com.rance.chatui.util.Utils;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.BubbleLinearLayout;
import com.rance.chatui.widget.GifTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatAcceptViewHolder extends BaseViewHolder<MessageInfo> {

    @Bind(R.id.chat_item_date)
    TextView chatItemDate;
    @Bind(R.id.chat_item_header)
    ImageView chatItemHeader;
    @Bind(R.id.chat_item_content_text)
    GifTextView chatItemContentText;
    @Bind(R.id.chat_item_content_image)
    BubbleImageView chatItemContentImage;
    @Bind(R.id.chat_item_voice)
    ImageView chatItemVoice;
    @Bind(R.id.chat_item_layout_content)
    BubbleLinearLayout chatItemLayoutContent;
    @Bind(R.id.chat_item_voice_time)
    TextView chatItemVoiceTime;
    private ChatAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    private RelativeLayout.LayoutParams layoutParams;

    public ChatAcceptViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler) {
        super(parent, R.layout.item_chat_accept);
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        layoutParams = (RelativeLayout.LayoutParams) chatItemLayoutContent.getLayoutParams();
    }

    @Override
    public void setData(MessageInfo data) {
        chatItemDate.setText(data.getTime() != null ? data.getTime() : "");
        Glide.with(getContext()).load(data.getHeader()).into(chatItemHeader);
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition());
            }
        });
        if (data.getContent() != null) {
            chatItemContentText.setSpanText(handler, data.getContent(), true);
            chatItemVoice.setVisibility(View.GONE);
            chatItemContentText.setVisibility(View.VISIBLE);
            chatItemLayoutContent.setVisibility(View.VISIBLE);
            chatItemVoiceTime.setVisibility(View.GONE);
            chatItemContentImage.setVisibility(View.GONE);
            TextPaint paint = chatItemContentText.getPaint();
            // 计算textview在屏幕上占多宽
            int len = (int) paint.measureText(chatItemContentText.getText().toString().trim());
            if (len < Utils.dp2px(getContext(), 200)){
                layoutParams.width = len + Utils.dp2px(getContext(), 30);
                layoutParams.height = Utils.dp2px(getContext(), 48);
            } else {
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
            chatItemLayoutContent.setLayoutParams(layoutParams);
        } else if (data.getImageUrl() != null) {
            chatItemVoice.setVisibility(View.GONE);
            chatItemLayoutContent.setVisibility(View.GONE);
            chatItemVoiceTime.setVisibility(View.GONE);
            chatItemContentText.setVisibility(View.GONE);
            chatItemContentImage.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(data.getImageUrl()).into(chatItemContentImage);
            chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onImageClick(chatItemContentImage, getDataPosition());
                }
            });
            layoutParams.width = Utils.dp2px(getContext(), 120);
            layoutParams.height = Utils.dp2px(getContext(), 48);
            chatItemLayoutContent.setLayoutParams(layoutParams);
        } else if (data.getFilepath() != null) {
            chatItemVoice.setVisibility(View.VISIBLE);
            chatItemLayoutContent.setVisibility(View.VISIBLE);
            chatItemContentText.setVisibility(View.GONE);
            chatItemVoiceTime.setVisibility(View.VISIBLE);
            chatItemContentImage.setVisibility(View.GONE);
            chatItemVoiceTime.setText(Utils.formatTime(data.getVoiceTime()));
            chatItemLayoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onVoiceClick(chatItemVoice, getDataPosition());
                }
            });
            layoutParams.width = Utils.dp2px(getContext(), 120);
            layoutParams.height = Utils.dp2px(getContext(), 48);
            chatItemLayoutContent.setLayoutParams(layoutParams);
        }
    }
}
