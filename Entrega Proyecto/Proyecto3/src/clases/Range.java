package clases;

import java.io.Serializable;

public class Range<T extends Comparable<? super T>> implements Serializable {
	private static final long serialVersionUID = -3579384219578136573L;
	/**
	 * 
	 */
	private T low;
    private T high;

    public Range(T low, T high) {
    	if (low == null || high == null || low.compareTo(high) > 0 || low.compareTo(getLowerBound()) < 0 || high.compareTo(getUpperBound()) >= 0) 
    	{
            throw new IllegalArgumentException("Rango no válido");
        }
        this.low = low;
        this.high = high;
    }

    public boolean contains(T o) 
    {
        return (o.compareTo(low) >= 0 && o.compareTo(high) <= 0);
    }
    @SuppressWarnings("unchecked")
	private T getLowerBound() {
        // Límite inferior (inclusive) del intervalo [0, 24)
        return (T) Integer.valueOf(0);
    }

    @SuppressWarnings("unchecked")
	private T getUpperBound() {
        // Límite superior (exclusivo) del intervalo [0, 24)
        return (T) Integer.valueOf(24);
    }
    
    public T getLow() {
		return low;
	}
    
    public T getHigh() {
		return high;
	}
}