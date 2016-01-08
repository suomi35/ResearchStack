package co.touchlab.researchstack.glue.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import co.touchlab.researchstack.core.ui.PassCodeActivity;
import co.touchlab.researchstack.glue.ObservableUtils;
import co.touchlab.researchstack.glue.R;
import co.touchlab.researchstack.glue.ResearchStack;

/**
 * Created by bradleymcdermott on 10/15/15.
 */
public class EmailVerificationActivity extends PassCodeActivity
{
    public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        initFileAccess();
    }

    @Override
    protected void onDataReady()
    {
        super.onDataReady();

        ResearchStack researchStack = ResearchStack.getInstance();

        this.password = getIntent().getStringExtra(EXTRA_PASSWORD);
        this.email =  researchStack.getDataProvider().getUserEmail(this);

        ((AppCompatImageView) findViewById(R.id.study_logo)).setImageResource(researchStack.getLargeLogoDiseaseIcon());
        ((AppCompatTextView) findViewById(R.id.email_verification_body)).setText(getString(R.string.email_verification_body,
                getString(researchStack.getAppName()), email));

        RxView.clicks(findViewById(R.id.email_verification_wrong_email))
                .subscribe(v -> changeEmail());

        RxView.clicks(findViewById(R.id.email_verification_resend))
                .subscribe(v -> resendVerificationEmail());

        RxView.clicks(findViewById(R.id.continue_button)).subscribe(v -> attemptSignIn());
    }

    private void changeEmail()
    {
        Toast.makeText(EmailVerificationActivity.this,
                "TODO change email address",
                Toast.LENGTH_SHORT).show();
    }

    private void resendVerificationEmail()
    {
        ResearchStack.getInstance()
                .getDataProvider().resendEmailVerification(email)
                .compose(ObservableUtils.applyDefault())
                .subscribe(dataResponse -> {
                    Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    // TODO Cast throwable to HttpException -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                    // Convert errorBody to JSON-String, convert json-string to object
                    // (BridgeMessageResponse) and pass BridgeMessageResponse.getMessage()to
                    // toast
                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void attemptSignIn()
    {
        Toast.makeText(EmailVerificationActivity.this, "TODO attempt sign in", Toast.LENGTH_SHORT)
                .show();
    }
}
