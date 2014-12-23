package net.coding.program.maopao.item;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import net.coding.program.Global;
import net.coding.program.R;
import net.coding.program.common.HtmlContent;
import net.coding.program.model.TaskObject;

/**
 * Created by chaochen on 14/12/22.
 */
public class ContentAreaBase {

    protected TextView content;
    protected Html.ImageGetter imageGetter;

    public ContentAreaBase(View convertView, View.OnClickListener onClickContent, Html.ImageGetter imageGetterParamer) {
        content = (TextView) convertView.findViewById(R.id.comment);
        content.setMovementMethod(LinkMovementMethod.getInstance());
        content.setOnClickListener(onClickContent);

        imageGetter = imageGetterParamer;
    }

    public void setData(TaskObject.TaskComment comment) {
        String data = comment.content;
        Global.MessageParse maopaoData = HtmlContent.parseTaskComment(data);

        if (maopaoData.text.isEmpty()) {
            content.setVisibility(View.GONE);
        } else {
            content.setTag(comment);
            content.setVisibility(View.VISIBLE);
            content.setText(Global.changeHyperlinkColor(maopaoData.text, imageGetter, Global.tagHandler));
        }
    }
}
