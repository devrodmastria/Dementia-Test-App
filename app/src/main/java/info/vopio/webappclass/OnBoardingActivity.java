package info.vopio.webappclass;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoardingActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Button nextBtn;
    private Button backBtn, finishBtn;

    private ImageView zero, one, two, three, four, five, six, seven, eight, nine;
    private ImageView[] indicators;

    private static int page = 0;   //  to track page position

    private static int yesCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        nextBtn = (Button) findViewById(R.id.intro_btn_next);

        backBtn = (Button) findViewById(R.id.intro_btn_back);
        finishBtn = (Button) findViewById(R.id.intro_btn_finish);

        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        three = (ImageView) findViewById(R.id.intro_indicator_3);
        four = (ImageView) findViewById(R.id.intro_indicator_4);
        five = (ImageView) findViewById(R.id.intro_indicator_5);
        six = (ImageView) findViewById(R.id.intro_indicator_6);
        seven = (ImageView) findViewById(R.id.intro_indicator_7);
        eight = (ImageView) findViewById(R.id.intro_indicator_8);
        nine = (ImageView) findViewById(R.id.intro_indicator_9);


        indicators = new ImageView[]{zero, one, two, three, four, five, six, seven, eight, nine};

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        final int color1 = ContextCompat.getColor(this, R.color.page_one);
        final int color2 = ContextCompat.getColor(this, R.color.page_two);
        final int color3 = ContextCompat.getColor(this, R.color.page_three);

        final int[] colorList = new int[]{color1, color2, color3, color1, color2, color3, color1, color2, color3, color1};

        final ArgbEvaluator evaluator = new ArgbEvaluator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                /*
                color update
                 */

                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 9 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);

            }

            @Override
            public void onPageSelected(int position) {

                page = position;

                updateIndicators(page);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        break;
                    case 3:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 4:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 5:
                        mViewPager.setBackgroundColor(color3);
                        break;
                    case 6:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 7:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 8:
                        mViewPager.setBackgroundColor(color3);
                        break;
                    case 9:
                        mViewPager.setBackgroundColor(color1);
                        break;
                }

                nextBtn.setVisibility(position == 9 ? View.GONE : View.VISIBLE);
                finishBtn.setVisibility(position == 9 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(page > 0){
                    page -= 1;
                }
                mViewPager.setCurrentItem(page, true);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(yesCounter >= 7){
                    // definitely consult a doctor
                    alertDialog("You have a " + (yesCounter * 10) + "% chance of having dementia.", "Your symptom count is "+ yesCounter + " out of 10.");
                }
                else if (yesCounter >= 4)
                {
                    // patient has mild dementia level
                    alertDialog("You have a " + (yesCounter * 10) + "% chance of having dementia.", "Your symptom count is "+ yesCounter + " out of 10.");
                } else
                {
                    alertDialog("You have a " + (yesCounter * 10) + "% chance of having dementia.", "Your symptom count is "+ yesCounter + " out of 10.");
                }

            }
        });

    }

    private void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

//        ImageView img;

        //int[] bgs = new int[]{R.drawable.ic_flight_24dp, R.drawable.ic_mail_24dp, R.drawable.ic_explore_24dp};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_description);
            TextView textTitle = (TextView) rootView.findViewById(R.id.section_title);
            AppCompatImageView imageView = (AppCompatImageView)rootView.findViewById(R.id.imageViewOnBoard);
            CheckBox checkBox = (CheckBox)rootView.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        yesCounter++;
                    }
                    else if(yesCounter > 0)
                    {
                        yesCounter--;
                    }
                }
            });

            Resources res = getResources();

            int section = getArguments().getInt(ARG_SECTION_NUMBER);

            switch (section){
                case 1:
                    textView.setText(getString(R.string.onboard_one));
                    textTitle.setText(getString(R.string.onboard_one_title));
                    break;
                case 2:
                    textView.setText(getString(R.string.onboard_two));
                    textTitle.setText(getString(R.string.onboard_two_title));
                    break;
                case 3:
                    textView.setText(getString(R.string.onboard_three));
                    textTitle.setText(getString(R.string.onboard_three_title));
                    break;
                case 4:
                    textView.setText(getString(R.string.onboard_four));
                    textTitle.setText(getString(R.string.onboard_four_title));
                    break;
                case 5:
                    textView.setText(res.getString(R.string.onboard_five));
                    textTitle.setText(getString(R.string.onboard_five_title));
                    break;
                case 6:
                    textView.setText(getString(R.string.onboard_six));
                    textTitle.setText(getString(R.string.onboard_six_title));
                    break;
                case 7:
                    textView.setText(getString(R.string.onboard_seven));
                    textTitle.setText(getString(R.string.onboard_seven_title));
                    break;
                case 8:
                    textView.setText(res.getString(R.string.onboard_eight));
                    textTitle.setText(getString(R.string.onboard_eight_title));
                    break;
                case 9:
                    textView.setText(getString(R.string.onboard_nine));
                    textTitle.setText(getString(R.string.onboard_nine_title));
                    break;
                case 10:
                    textView.setText(getString(R.string.onboard_ten));
                    textTitle.setText(getString(R.string.onboard_ten_title));
                    break;
                default:
                    textView.setText(getString(R.string.onboard_three));
                    Log.d("Log", "Default onboard view " + ARG_SECTION_NUMBER);
                    break;
            }

            return rootView;
        }

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);

        }

        @Override
        public int getCount() {
            // Show number total pages.
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
                case 5:
                    return "SECTION 6";
                case 6:
                    return "SECTION 7";
                case 7:
                    return "SECTION 8";
                case 8:
                    return "SECTION 9";
                case 9:
                    return "SECTION 10";
                case 10:
            }
            return null;
        }

    }

    private void alertDialog(String title, String message) {
        final AlertDialog.Builder confirmDailog = new AlertDialog.Builder(this);
        confirmDailog.setTitle(title);

        String finalMessage = message + "\n\nREMINDER: " + getResources().getString(R.string.disclaimer) + "\n\n"
                + getResources().getString(R.string.learn_more);

        confirmDailog.setMessage(finalMessage);

        confirmDailog.setCancelable(false);
        confirmDailog.setPositiveButton("Learn More", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.betterhealth.vic.gov.au/health/conditionsandtreatments/dementia-early-signs"));
                startActivity(browserIntent);
            }
        });
        confirmDailog.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnBoardingActivity.super.finish();
            }
        });

        confirmDailog.create().show();
    }

}
