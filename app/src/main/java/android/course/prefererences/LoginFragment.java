package android.course.prefererences;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends DialogFragment implements View.OnClickListener {

    EditText etMail, etPassword;
    Button btnLogin;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etMail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = etMail.getText().toString();
        String password = etPassword.getText().toString();

        //Inform the listener about the userName....Logged in.
        listener.onLogin(email, true);

        dismiss();
    }

    //we need a listener....
    private OnLoginListener listener;


    public interface OnLoginListener {
        void onLogin (String name, boolean isLoggedIn);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginListener) {
            //save it as a listener:
            listener = (OnLoginListener) context;
        }

        else {
            throw new RuntimeException(
                    context +
                            " Must Implement " +
                            OnLoginListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
