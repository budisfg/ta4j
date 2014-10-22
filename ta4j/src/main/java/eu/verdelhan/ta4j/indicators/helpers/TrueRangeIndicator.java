/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Marc de Verdelhan & respective authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.indicators.helpers;

import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.TADecimal;
import eu.verdelhan.ta4j.TimeSeries;

/**
 * True range indicator.
 * <p>
 */
public class TrueRangeIndicator implements Indicator<TADecimal>{

    private TimeSeries series;

    public TrueRangeIndicator(TimeSeries series) {
        this.series = series;
    }
    
    @Override
    public TADecimal getValue(int index) {
        TADecimal ts = series.getTick(index).getMaxPrice().minus(series.getTick(index).getMinPrice());
        TADecimal ys = index == 0 ? TADecimal.ZERO : series.getTick(index).getMaxPrice().minus(series.getTick(index - 1).getClosePrice());
        TADecimal yst = index == 0 ? TADecimal.ZERO : series.getTick(index - 1).getClosePrice().minus(series.getTick(index).getMinPrice());
        
        return ts.abs().max(ys.abs()).max(yst.abs());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
