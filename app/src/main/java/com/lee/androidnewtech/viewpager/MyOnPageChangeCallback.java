package com.lee.androidnewtech.viewpager;

import androidx.viewpager2.widget.ViewPager2;

public class MyOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

    private static final String TAG = "MyOnPageChangeCallback";
    private ViewPager2 vp2;
    // fragment的个数
    private int fragmentSize;
    //记录上一次滑动的positionOffsetPixels值
    private int lastValue = -1;
    // 滑动方向
    private boolean turnToLeft = false;
    private boolean isScrolling = false;
    private ChangeViewCallback changeViewCallback;
    //最后位置
    private int lastPosition = -1;
    private int mState;
    //当前真实下标
    private int realPosition = 0;
    private int virtualPosition = 0;

    public MyOnPageChangeCallback(ViewPager2 vp2, int fragmentSize) {
        this.vp2 = vp2;
        this.fragmentSize = fragmentSize;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (isScrolling) {
//            MyLogger.i(TAG,">>滑动中>>>position:"+position+">>>positionOffset:"+positionOffset+">>>>positionOffsetPixels:"+positionOffsetPixels);
            if (lastValue > positionOffsetPixels) {
//                MyLogger.i(TAG,"// 递减，向左侧滑动、上");
                turnToLeft = false;
            } else if (lastValue < positionOffsetPixels) {
//                MyLogger.i(TAG,"// 递增，向右侧滑动，下");
                turnToLeft = true;
            }
        }
        lastValue = positionOffsetPixels;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        realPosition = position;
        if (mState == ViewPager2.SCROLL_STATE_SETTLING) {
            if (changeViewCallback != null && lastPosition != position) {
                if (turnToLeft) {
                    virtualPosition++;
                }
                if (!turnToLeft) {
                    virtualPosition--;
                }
                changeViewCallback.changeView(turnToLeft, virtualPosition);
                lastPosition = position;
            }
            turnToLeft = false;
//            MyLogger.i(TAG, "滑动结束》》》》lastPosition:" + lastPosition + "virtualPosition:" + position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        isScrolling = state == ViewPager2.SCROLL_STATE_DRAGGING;
        this.mState = state;
        if (state == ViewPager2.SCROLL_STATE_IDLE) {
            // 小于2个没有必要翻页
            if (fragmentSize < 2) {
                return;
            }
//            MyLogger.i(TAG, "当前真实下标：" + realPosition + "virtualPosition:" + virtualPosition);
            // 没有做无限滚动哦，只能从 1页翻到 n页
            if (virtualPosition < 0 || virtualPosition > fragmentSize - 1) {
                return;
            }

            //当前页码判断可知道是第一页还是最后一页
            if (realPosition == fragmentSize - 1 && virtualPosition == fragmentSize - 1) {
                //最后一页-》切换为第一页
                vp2.setCurrentItem(0, false);
                realPosition = 0;
            } else if (realPosition == 0 && virtualPosition == 0) {
                //第一页-》切换为最后一页
                vp2.setCurrentItem(fragmentSize - 1, false);
                realPosition = fragmentSize - 1;
            }
            virtualPosition = realPosition;
        }
    }

    /**
     * 滑动状态改变回调
     */
    public interface ChangeViewCallback {
        /**
         * 切换视图 ？决定于left和right 。
         *
         * @param turnToLeft
         * @param virtualPosition
         */
        public void changeView(boolean turnToLeft, int virtualPosition);
    }

    public void setChangeViewCallback(ChangeViewCallback callback) {
        changeViewCallback = callback;
    }
}
