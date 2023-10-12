package in.succinct.beckn;

import in.succinct.json.JSONAwareWrapper;
import org.json.simple.JSONAware;

@SuppressWarnings("all")
public interface TagGroupHolder {
    default TagGroups getTags(){
        return get(TagGroups.class, "tags");
    }


    default void setTags(TagGroups tags){
        set("tags",tags);
    }

    default String getTag(String tagGroup, String code) {
        TagGroups groups = getTags();
        return groups == null ? null : groups.getTag(tagGroup,code);
    }
    default void setTag(String tagGroup, String code, Object value){
        TagGroups groups = getTags();
        if (groups == null) {
            groups = new TagGroups();
            setTags(groups);
        }
        groups.setTag(tagGroup,code,value == null ? null : String.valueOf(value));
    }


    <W extends JSONAwareWrapper> W get(Class<W> clazz, String name);
    <W extends JSONAwareWrapper> void set(String tags, W tagGroups);

}
