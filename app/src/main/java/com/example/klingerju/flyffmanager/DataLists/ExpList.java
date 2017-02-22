package com.example.klingerju.flyffmanager.DataLists;


import com.example.klingerju.flyffmanager.Classes.Level;

import java.util.ArrayList;

/**
 * Created by julianklinger on 21.02.17.
 */

public class ExpList {
    public static ExpList expList;

    public static ExpList getExpList() {
        if (expList == null) {
            expList = new ExpList();
        }

        return expList;
    }

    public ArrayList<Level> levels = new ArrayList<>();
    public ArrayList<Level> levelsMasterHero = new ArrayList<>();

    public ExpList() {
        levels.add(new Level(1, 14L));
        levels.add(new Level(2, 20L));
        levels.add(new Level(3, 36L));
        levels.add(new Level(4, 90L));
        levels.add(new Level(5, 152L));
        levels.add(new Level(6, 250L));
        levels.add(new Level(7, 352L));
        levels.add(new Level(8, 480L));
        levels.add(new Level(9, 591L));
        levels.add(new Level(10, 743L));
        levels.add(new Level(11, 973L));
        levels.add(new Level(12, 1290L));
        levels.add(new Level(13, 1632L));
        levels.add(new Level(14, 1928L));
        levels.add(new Level(15, 2340L));
        levels.add(new Level(16, 3480L));
        levels.add(new Level(17, 4125L));
        levels.add(new Level(18, 4995L));
        levels.add(new Level(19, 5880L));
        levels.add(new Level(20, 7840L));
        levels.add(new Level(21, 6875L));
        levels.add(new Level(22, 8243L));
        levels.add(new Level(23, 10380L));
        levels.add(new Level(24, 13052L));
        levels.add(new Level(25, 16450L));
        levels.add(new Level(26, 20700L));
        levels.add(new Level(27, 26143L));
        levels.add(new Level(28, 31950L));
        levels.add(new Level(29, 38640L));
        levels.add(new Level(30, 57035L));
        levels.add(new Level(31, 65000L));
        levels.add(new Level(32, 69125L));
        levels.add(new Level(33, 72000L));
        levels.add(new Level(34, 87239L));
        levels.add(new Level(35, 105863L));
        levels.add(new Level(36, 128694L));
        levels.add(new Level(37, 182307L));
        levels.add(new Level(38, 221450L));
        levels.add(new Level(39, 269042L));
        levels.add(new Level(40, 390368L));
        levels.add(new Level(41, 438550L));
        levels.add(new Level(42, 458137L));
        levels.add(new Level(43, 468943L));
        levels.add(new Level(44, 560177L));
        levels.add(new Level(45, 669320L));
        levels.add(new Level(46, 799963L));
        levels.add(new Level(47, 1115396L));
        levels.add(new Level(48, 1331100L));
        levels.add(new Level(49, 1590273L));
        levels.add(new Level(50, 2306878L));
        levels.add(new Level(51, 2594255L));
        levels.add(new Level(52, 2711490L));
        levels.add(new Level(53, 2777349L));
        levels.add(new Level(54, 3318059L));
        levels.add(new Level(55, 3963400L));
        levels.add(new Level(56, 4735913L));
        levels.add(new Level(57, 6600425L));
        levels.add(new Level(58, 7886110L));
        levels.add(new Level(59, 9421875L));
        levels.add(new Level(60, 13547310L));
        levels.add(new Level(60, 13547310L));
        levels.add(new Level(61, 15099446L));
        levels.add(new Level(62, 15644776L));
        levels.add(new Level(63, 15885934L));
        levels.add(new Level(64, 18817757L));
        levels.add(new Level(65, 22280630L));
        levels.add(new Level(66, 26392968L));
        levels.add(new Level(67, 36465972L));
        levels.add(new Level(68, 43184958L));
        levels.add(new Level(69, 51141217L));
        levels.add(new Level(70, 73556918L));
        levels.add(new Level(71, 81991117L));
        levels.add(new Level(72, 84966758L));
        levels.add(new Level(73, 86252845L));
        levels.add(new Level(74, 102171368L));
        levels.add(new Level(75, 120995493L));
        levels.add(new Level(76, 143307208L));
        levels.add(new Level(77, 198000645L));
        levels.add(new Level(78, 234477760L));
        levels.add(new Level(79, 277716683L));
        levels.add(new Level(80, 381795797L));
        levels.add(new Level(81, 406848219L));
        levels.add(new Level(82, 403044458L));
        levels.add(new Level(83, 391191019L));
        levels.add(new Level(84, 442876559L));
        levels.add(new Level(85, 501408635L));
        levels.add(new Level(86, 567694433L));
        levels.add(new Level(87, 749813704L));
        levels.add(new Level(88, 849001357L));
        levels.add(new Level(89, 961154774L));
        levels.add(new Level(90, 1309582668L));
        levels.add(new Level(91, 1382799035L));
        levels.add(new Level(92, 1357505030L));
        levels.add(new Level(93, 1305632790L));
        levels.add(new Level(94, 1464862605L));
        levels.add(new Level(95, 1628695740L));
        levels.add(new Level(96, 1810772333L));
        levels.add(new Level(97, 2348583653L));
        levels.add(new Level(98, 2611145432L));
        levels.add(new Level(99, 2903009208L));
        levels.add(new Level(100, 3919352097L));
        levels.add(new Level(101, 4063358600L));
        levels.add(new Level(102, 3916810682L));
        levels.add(new Level(103, 4314535354L));
        levels.add(new Level(104, 4752892146L));
        levels.add(new Level(105, 5235785988L));
        levels.add(new Level(106, 5767741845L));
        levels.add(new Level(107, 6353744416L));
        levels.add(new Level(108, 6999284849L));
        levels.add(new Level(109, 7710412189L));
        levels.add(new Level(110, 8493790068L));
        levels.add(new Level(111, 9356759139L));
        levels.add(new Level(112, 10307405867L));
        levels.add(new Level(113, 11354638303L));
        levels.add(new Level(114, 12508269555L));
        levels.add(new Level(115, 13779109742L));
        levels.add(new Level(116, 15179067292L));
        levels.add(new Level(117, 16721260528L));
        levels.add(new Level(118, 18420140598L));
        levels.add(new Level(119, 20291626883L));
        levels.add(new Level(120, 22353256174L));
        levels.add(new Level(121, 24624347002L));
        levels.add(new Level(122, 27126180657L));
        levels.add(new Level(123, 29882200612L));
        levels.add(new Level(124, 32918232194L));
        levels.add(new Level(125, 36262724585L));
        levels.add(new Level(126, 39947017402L));
        levels.add(new Level(127, 44005634371L));
        levels.add(new Level(128, 48476606823L));
        levels.add(new Level(129, 53401830076L));
        levels.add(new Level(130, 58827456011L));
        levels.add(new Level(131, 64804325542L));
        levels.add(new Level(132, 71388445017L));
        levels.add(new Level(133, 78641511031L));
        levels.add(new Level(134, 86631488552L));
        levels.add(new Level(135, 95433247789L));
        levels.add(new Level(136, 105129265764L));
        levels.add(new Level(137, 115810399166L));
        levels.add(new Level(138, 127576735721L));
        levels.add(new Level(139, 140538532070L));
        levels.add(new Level(140, 154817246928L));
        levels.add(new Level(141, 170546679216L));
        levels.add(new Level(142, 187874221825L));
        levels.add(new Level(143, 206962242762L));
        levels.add(new Level(144, 227989606627L));
        levels.add(new Level(145, 251153350660L));
        levels.add(new Level(146, 276670531087L));
        levels.add(new Level(147, 304780257046L));
        levels.add(new Level(148, 335745931162L));
        levels.add(new Level(149, 369857717768L));
    }
}
