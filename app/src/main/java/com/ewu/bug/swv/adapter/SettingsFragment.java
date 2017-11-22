package com.ewu.bug.swv.adapter;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;

import com.ewu.bug.swv.AboutActivity;
import com.ewu.bug.swv.MainApplication;
import com.ewu.bug.swv.ProfileActivity;
import com.ewu.bug.swv.R;
import com.ewu.bug.swv.SettingsActivity;
import com.ewu.bug.swv.hotkey.HKService;
import com.ewu.bug.swv.shake.ShakeService;
import com.ewu.bug.swv.utils.MixTools;
import com.ewu.bug.swv.utils.PfKey;
import com.ewu.bug.swv.utils.PrefUtils;
import com.ewu.bug.swv.utils.ServiceTools;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import xplo.library.ekush.MyBanglaSupport;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    //private MainActivity mMainActivity;

    private SettingsActivity mSettingsActivity;

    private Preference pfProfile;
    private Preference pfRate;
    private Preference pfShare;
    private Preference pfFeedback;
    private Preference pfAbout;
    private Preference pfResetAll;
    private ListPreference pfShakeType;
    private ListPreference pfHotKeySensivity;

    private PreferenceScreen mPreferenceScreen;

    Typeface tf=null;


    public SettingsFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //mMainActivity = (MainActivity)activity;
        mSettingsActivity = (SettingsActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //to reset DefaultSharedPreferences
        //PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().clear().commit();

        addPreferencesFromResource(R.xml.settings);

        pfRate = findPreference(PfKey.pfRate);
        pfShare = findPreference(PfKey.pfShare);
        pfFeedback = findPreference(PfKey.pfFeedback);
        pfProfile = findPreference(PfKey.pfProfile);
        pfAbout = findPreference(PfKey.pfAbout);
        pfResetAll = findPreference(PfKey.pfResetAll);
        pfShakeType = (ListPreference) findPreference(PfKey.pfShakeType);
        pfHotKeySensivity = (ListPreference) findPreference(PfKey.pfHotKeySensivity);


        pfRate.setEnabled(false);

        //typefaceBangla();



        mPreferenceScreen = getPreferenceScreen();


        pfRate.setOnPreferenceClickListener(this);
        pfShare.setOnPreferenceClickListener(this);
        pfFeedback.setOnPreferenceClickListener(this);
        pfProfile.setOnPreferenceClickListener(this);
        pfAbout.setOnPreferenceClickListener(this);
        pfResetAll.setOnPreferenceClickListener(this);

        //shake type
        if (pfShakeType.getValue() == null) {
            // to ensure we don't get a null value
            // set first value by default
            pfShakeType.setValueIndex(0);
        }

        String sum = MixTools.getShakeType(pfShakeType.getValue().toString());
        pfShakeType.setSummary(sum);
        pfShakeType.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                //preference.setSummary(newValue.toString());
                String sum = MixTools.getShakeType(newValue.toString());
                preference.setSummary(sum);

                return true;
            }
        });


        //hot key sensivity
         if (pfHotKeySensivity.getValue() == null) {
            // to ensure we don't get a null value
            // set first value by default
             pfHotKeySensivity.setValueIndex(0);
        }

        String sensivity = MixTools.getHotKeySensivity(pfHotKeySensivity.getValue().toString());
        pfHotKeySensivity.setSummary(sensivity);
        pfHotKeySensivity.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                //preference.setSummary(newValue.toString());
                String sensivity = MixTools.getHotKeySensivity(newValue.toString());
                preference.setSummary(sensivity);

                return true;
            }
        });




//        ListPreference listPreference = (ListPreference) findPreference("preference_key");

//        if(listPreference.getValue()==null) {
//            // to ensure we don't get a null value
//            // set first value by default
//            listPreference.setValueIndex(0);
//        }
//        listPreference.setSummary(listPreference.getValue().toString());
//        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                preference.setSummary(newValue.toString());
//                return true;
//            }
//        });


        String s = "";
        if (ServiceTools.isMyServiceRunning(getActivity(), HKService.class)) {
            //pToast("Service Running");
            //ServiceTools.setKeyGuard(MainApplication.getContext(), false);
            s = "HK R";

        } else {
            //pToast("Service Stopped");
            //ServiceTools.setKeyGuard(MainApplication.getContext(), true);
            s = "HK X";

        }

        s = s + "\n";
        if (ServiceTools.isMyServiceRunning(getActivity(), ShakeService.class)) {
            //pToast("Service Running");
            //ServiceTools.setKeyGuard(MainApplication.getContext(), false);
            s += "Shake R";

        } else {
            //pToast("Service Stopped");
            //ServiceTools.setKeyGuard(MainApplication.getContext(), true);
            s += "Shake X";

        }

        Log.d("xxx", "Service:\n" + s);
        //pToast(s);


//        pfRate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//
//                pToast("pfRate");
//                return false;
//            }
//        });

    }

    public void shareApp() {

        pToast("Long Click to Paste Content on Facebook.");
        ClipboardManager mClipboardManager = (ClipboardManager) getActivity()
                .getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData;


        String textToShareAppPromote = "Download this Hot android App on Women's Right.";
        mClipData = ClipData.newPlainText("text", textToShareAppPromote);
        mClipboardManager.setPrimaryClip(mClipData);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShareAppPromote);
        getActivity().startActivity(Intent.createChooser(intent,
                "Share " + getActivity().getString(R.string.app_name)));

    }

    public void feedback() {
        try {
            Intent Email = new Intent(Intent.ACTION_SEND);
            // Email.setType("text/email");
            Email.setType("plain/text");
            // Email.setType("message/rfc822");

            Email.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{"nasif.002@gmail.com"});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback " + "Stop Women Violence");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n..." + "");
            getActivity().startActivity(Intent.createChooser(Email, "Send Feedback"));

        } catch (Exception e) {
        }
    }


    private void typefaceBangla() {

        tf = Typeface.createFromAsset(getActivity().getAssets(),
                getString(R.string.font_bn));

        pfRate.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_rate), tf));

        pfShare.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_share), tf));
        pfShare.setSummary(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_share_sum), tf));

        pfFeedback.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_feedback), tf));

        pfProfile.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_profile), tf));
        pfProfile.setSummary(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_profile_sum), tf));

        pfAbout.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_app_name), tf));
        pfAbout.setSummary(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_version1), tf));

        pfShakeType.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_shake_mode), tf));
        pfShakeType.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_shake_sum_on), tf));


        Preference pfHotKey = findPreference(PfKey.pfHotKey);
        Preference pfShake = findPreference(PfKey.pfShake);
        //Preference pfShakeType = findPreference(PfKey.pfShakeType);

        pfShake.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_shake), tf));
        pfShake.setSummary(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_shake_sum_on), tf));

        pfHotKey.setTitle(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_hot_key), tf));
        pfHotKey.setSummary(MyBanglaSupport.getBanglaSpnString(getString(R.string.bn_hot_key_sum_on), tf));




    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_simple, container,
//                false);
//
//        return rootView;
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //pToast("triggered");
        //pToast(key);

        //Preference pref = findPreference(key);


        if (key.equals(PfKey.pfShake)) {
            //pToast("pfShake");
            boolean b = sharedPreferences.getBoolean(PfKey.pfShake, true);
            Intent intent = new Intent(getActivity(), ShakeService.class);

            if (b) {
                getActivity().startService(intent);
            } else {
                //pToast("Stop Shake Service");
                getActivity().stopService(intent);
            }


        } else if (key.equals(PfKey.pfShakeType)) {
            //pToast("pfShakeType");

//            String s = sharedPreferences.getString(key,"1");
//            pToast(s);

        } else if (key.equals(PfKey.pfHotKey)) {
            //pToast("pfHotKey");
            boolean b = sharedPreferences.getBoolean(PfKey.pfHotKey, true);
            //Intent intent = new Intent(getActivity(), HKService.class);

            if (b) {
                //getActivity().startService(intent);
                setHotKey(true);

            } else {
                //getActivity().stopService(intent);
                setHotKey(false);
            }


        }


    }

    private void setHotKey(boolean b) {

        Context mContext = MainApplication.getContext();
        boolean isServiceRunning = ServiceTools.isMyServiceRunning(mContext, HKService.class);

        if (b) {

//            if(!isServiceRunning){
//                return;
//            }

            ServiceTools.setKeyGuard(MainApplication.getContext(), false);
            getActivity().startService(new Intent(getActivity(), HKService.class));

        } else {
//            if(!isServiceRunning){
//                return;
//            }
            ServiceTools.setKeyGuard(MainApplication.getContext(), true);
            getActivity().stopService(new Intent(getActivity(),
                    HKService.class));

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void pToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onPreferenceClick(Preference pref) {
        //pToast("onPreferenceClick");

        String key = pref.getKey();
//
        if(key.equals("pfRate")){
            //pToast("Rate");
            return  true;

        }else if (key.equals(PfKey.pfProfile)) {

            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
            return  true;
        } else if (key.equals(PfKey.pfAbout)) {

            Intent intent = new Intent(getActivity(), AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (key.equals(PfKey.pfShare)) {

            //pToast("Share");
           shareApp();
            return true;
        } else if (key.equals(PfKey.pfFeedback)) {
            //pToast("Feedback");
            feedback();
            return true;
        } else if (key.equals(PfKey.pfResetAll)) {
            //pToast("Feedback");
            //feedback();
            PrefUtils.clear();
            MaterialShowcaseView.resetAll(getActivity());
            pToast("Reseted");

            return true;
        }


        return false;
    }
}
