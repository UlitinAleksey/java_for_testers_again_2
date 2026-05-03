package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.collections.common.Common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name : List.of("","group name")) {
//            for (var header : List.of("","group header")){
//                for (var footer : List.of("","group footer")){
//                    result.add((new GroupData().withName(name)
//                            .withHeader(header)
//                            .withFooter(footer)));
//                }
//            }
//        }
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)
//        ){
//        var line = breader.readLine();
//        while (line !=null) {
//            json = json + line;
//            line = breader.readLine();
//        }
//        }
       // var json = Files.readString(Paths.get("groups.json"));
        var mapper = new ObjectMapper();
        //var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.json"),new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> randomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(Common.randomString(10))
                .withHeader(Common.randomString(20))
                .withFooter(Common.randomString(30));
       return Stream.generate(randomGroup).limit(1);
    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();

//        Comparator<GroupData> compareById = (o1, o2) ->
//                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//
//        newGroups.sort(compareById);

        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));  // footer остается исходным
//        expectedList.sort(compareById);

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
//
//        var newUiGroups = app.groups().getList();
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'","","")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(newGroups,oldGroups);

    }


}
