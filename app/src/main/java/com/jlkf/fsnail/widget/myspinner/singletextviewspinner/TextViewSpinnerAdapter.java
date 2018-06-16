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

package com.jlkf.fsnail.widget.myspinner.singletextviewspinner;

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

import java.util.List;

public class TextViewSpinnerAdapter<T> extends BaseAdapter {

  private int textColor;
  private int backgroundSelector;
  private final List<T> items;
  private final Context context;
  private int selectedIndex;
  private boolean isHintEnabled;

  public TextViewSpinnerAdapter(Context context, List<T> items) {
    this.context = context;
    this.items = items;
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }

  public void notifyItemSelected(int index) {
    selectedIndex = index;
  }

  public void setHintEnabled(boolean isHintEnabled) {
    this.isHintEnabled = isHintEnabled;
  }

  public boolean isHintEnabled() {
    return this.isHintEnabled;
  }

  public TextViewSpinnerAdapter<T> setTextColor(@ColorInt int textColor) {
    this.textColor = textColor;
    return this;
  }

  public TextViewSpinnerAdapter<T> setBackgroundSelector(@DrawableRes int backgroundSelector) {
    this.backgroundSelector = backgroundSelector;
    return this;
  }

  public List<T> getItems(){
    return items;
  }

  @Override
  public int getCount() {
    int size = items.size();
    if (size == 1 || isHintEnabled()) return size;
    return size - 1;
  }

  @Override
  public T getItem(int position) {
    if (isHintEnabled()) {
      return items.get(position);
    } else if (position >= getSelectedIndex() && items.size() != 1) {
      return items.get(position + 1);
    } else {
      return items.get(position);
    }
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    ViewHolder holder = null;
    if (convertView == null) {
      LayoutInflater inflater = LayoutInflater.from(context);
      convertView = inflater.inflate(R.layout.item_textview, viewGroup, false);
      holder = new ViewHolder();

      holder.ll_spinner = (LinearLayout) convertView.findViewById(R.id.ll_spinner);
      holder.serviceName = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);
      holder.serviceName.setTextColor(textColor);
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

    holder.serviceName.setText(getItem(position).toString());
    return convertView;
  }

  private static class ViewHolder {
    LinearLayout ll_spinner;
    TextView serviceName;
  }

}