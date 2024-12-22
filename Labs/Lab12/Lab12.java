/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Labs                        ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *19.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Lab12 Exercises                                          ██████ 
██████                                                                        ██████
██████ *Proposed grade: ?                                                     ██████ 
██████                                                                        ██████
██████ *Website: https://efekurucay.com                                       ██████
██████                                                                        ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *    ███████ ███████ ███████   |    ███████ ███████ ███████            ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    █████   █████   █████     |    █████   █████   █████              ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    ███████ ██      ███████   |    ███████ ██      ███████            ██████ 
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████                          
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lab12 
{
    public static void main(String[] args) {      
          Map<String, Integer> map = new Map<>();        
        
        
    
    }
}

interface IEntry<K,V>{  K getKey();V getValue();void setValue(V value);  } 

class Entry<K,V> implements IEntry<K,V> 
{
private K key;
    private V value;  
        public Entry(K key, V value) {
            this.key = key;this.value = value;
        }
        
    @Override
    public K getKey() { return key; }
    @Override
        public V getValue() { return value; }
@Override
    public void setValue(V value) { this.value = value; }
}

interface IList<T> {
    int size();
    boolean isEmpty();
}

interface IMap<K, V> extends IList<K> {
    V put(K key, V value);
    V remove(K key);
    V get(K key);
    Iterable<Entry<K, V>> entrySet();
    Iterable<K> keySet();
    Iterable<V> values();
}

class Map<K,V> implements IMap<K,V> {
    private static final int DEFAULT_CAPACITY=12546;private Entry<K,V>[] table;private int size=0;  
    
    
    public Map() {
        table = new Entry[DEFAULT_CAPACITY];
    }

    private int hash(K key) {return Math.abs(Objects.hashCode(key) % table.length);}

    private void resize() 
    {
        if (size >= table.length * 0.75) {            
            Entry<K,V>[] newTable = new Entry[table.length * 2];
            for(Entry<K,V> entry:table){if(entry!=null&&entry.getKey()!=null){int index=hash(entry.getKey());while(newTable[index]!=null){index=(index+1)%newTable.length;}newTable[index]=entry;}}
            table = newTable;
        }
    }

    @Override
    public V put(K key, V value) {
        resize();
        int index = hash(key);
        
        while (table[index] != null) {
            if (Objects.equals(table[index].getKey(), key)) {
                V oldValue = table[index].getValue();
                table[index].setValue(value);
                return oldValue;
            }
            index = (index + 1) % table.length;
        }
        
        table[index] = new Entry<>(key, value);
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        int startIndex = index;
        
        while (table[index] != null) {
            if (Objects.equals(table[index].getKey(), key)) {
                V oldValue = table[index].getValue();
                table[index] = null;
                size--;
               
                index = (index + 1) % table.length;
                while (table[index] != null) {
                    Entry<K, V> entry = table[index];
                    table[index] = null;
                    size--;
                    put(entry.getKey(), entry.getValue());
                    index = (index + 1) % table.length;
                }
                return oldValue;
            }
            index = (index + 1) % table.length;
            if (index == startIndex) break;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        int startIndex = index;
        
        while (table[index] != null) {
            if (Objects.equals(table[index].getKey(), key)) {
                return table[index].getValue();
            }
            index = (index + 1) % table.length;
            if (index == startIndex) break;
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null && entry.getKey() != null) {
                entries.add(entry);
            }
        }
        return entries;
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null && entry.getKey() != null) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null && entry.getKey() != null) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public int size() {
        return size;
    }

    @Override public boolean isEmpty() 
    {
        return size == 0;
    }
}