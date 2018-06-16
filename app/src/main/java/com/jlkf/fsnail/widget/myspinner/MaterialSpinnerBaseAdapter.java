/*
 * Copyright (C) 2016 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jlkf.fsnail.widget.myspinner;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.MenuBean;

import java.util.List;

public abstract class MaterialSpinnerBaseAdapter<T> extends BaseAdapter {

  private final Context context;
  private int selectedIndex;
  private int textColor;
  private int backgroundSelector;
  private boolean isHintEnabled;

  public MaterialSpinnerBaseAdapter(Context context) {
    this.context = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final TextView textView;
    final TextView tv_price;
    final LinearLayout ll_spinner;
    ViewHolder holder = null;
    if (convertView == null) {
      LayoutInflater inflater = LayoutInflater.from(context);
      convertView = inflater.inflate(R.layout.ms__list_item, parent, false);
      holder = new ViewHolder();

      holder.ll_spinner = (LinearLayout) convertView.findViewById(R.id.ll_spinner);
      holder.serviceName = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);
      holder.price = (TextView)convertView.findViewById(R.id.tv_price);
      holder.serviceName.setTextColor(textColor);
      holder.price.setTextColor(textColor);
      if (backgroundSelector != 0) {
        holder.ll_spinner.setBackgroundResource(backgroundSelector);
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Configuration config = context.getResources().getConfiguration();
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
          holder.serviceName.setTextDirection(View.TEXT_DIRECTION_RTL);
        }
      }
      convertView.setTag(holder);
    } else {
      holder = ((ViewHolder) convertView.getTag());
    }

//    holder.serviceName.setText(((MenuBean)(getItem(position))).getServiceNmae());
//    holder.price.setText(((MenuBean)(getItem(position))).getPrice());
    return convertView;
  }



  public int getSelectedIndex() {
    return selectedIndex;
  }

  public void notifyItemSelected(int index) {
    selectedIndex = index;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public abstract T getItem(int position);

  @Override
  public abstract int getCount();

  public abstract T get(int position);

  public abstract List<T> getItems();

  public void setHintEnabled(boolean isHintEnabled) {
    this.isHintEnabled = isHintEnabled;
  }

  public boolean isHintEnabled() {
    return this.isHintEnabled;
  }

  public MaterialSpinnerBaseAdapter<T> setTextColor(@ColorInt int textColor) {
    this.textColor = textColor;
    return this;
  }

  public MaterialSpinnerBaseAdapter<T> setBackgroundSelector(@DrawableRes int backgroundSelector) {
    this.backgroundSelector = backgroundSelector;
    return this;
  }

  private static class ViewHolder {
      LinearLayout ll_spinner;
     TextView serviceName;
     TextView price;

  }
}
