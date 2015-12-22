package co.touchlab.researchstack.core.ui.scene;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.touchlab.researchstack.core.R;

public class TextQuestionScene extends SceneImpl<String>
{

    public TextQuestionScene(Context context)
    {
        super(context);
    }

    public TextQuestionScene(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TextQuestionScene(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View onCreateBody(LayoutInflater inflater, ViewGroup parent)
    {
        EditText editText = (EditText) inflater.inflate(R.layout.item_edit_text, null);
        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                getStepResult().setResult(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });

        String stringResult = getStepResult().getResult();
        if (!TextUtils.isEmpty(stringResult))
        {
            editText.setText(stringResult);
        }

        return editText;
    }

}