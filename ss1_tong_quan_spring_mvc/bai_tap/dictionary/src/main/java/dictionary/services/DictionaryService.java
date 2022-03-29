package dictionary.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictionaryService implements IDictionaryService{
    private static Map<String, String> dictionaryMap = new HashMap<>();
    static {
        dictionaryMap.put("một","one");
        dictionaryMap.put("hai","two");
        dictionaryMap.put("ba","three");
        dictionaryMap.put("bốn","four");
        dictionaryMap.put("năm","five");
        dictionaryMap.put("sáu","six");
        dictionaryMap.put("bảy","seven");
        dictionaryMap.put("tám","eight");
        dictionaryMap.put("chín","nine");
        dictionaryMap.put("mười","ten");
    }

    @Override
    public Map<String, String> findAll() {
        return new HashMap<>(dictionaryMap);
    }
}
