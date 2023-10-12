package in.succinct.beckn;

import org.json.simple.JSONArray;

public class TagGroups extends BecknObjectsWithId<TagGroup>{
    public TagGroups() {
    }

    public TagGroups(JSONArray array) {
        super(array);
    }

    public String getTag(String tagGroup, String code) {
        TagGroup tg = get(tagGroup);
        TagGroups tagList = tg == null ? null : tg.getList();
        TagGroup tag = tagList == null ? null : tagList.get(code);
        return tag == null ? null : tag.getValue();
    }
    public void setTag(String tagGroup, String code, String value){
        TagGroup tg = get(tagGroup);
        if (tg == null){
            tg = new TagGroup();
            tg.setId(tagGroup);
            add(tg);
        }
        TagGroups tagList = tg.getList();
        if (tagList == null){
            tagList = new TagGroups();
            tg.setList(tagList);
        }

        TagGroup tag = tagList.get(code);
        if (tag == null){
            if (value != null) {
                tag = new TagGroup();
                tag.setId(code);
                tag.setValue(value);
                tagList.add(tag);
            }
        }else if (value != null){
            tag.setValue(value);
        }else {
            tagList.remove(tag);
        }
    }
}
