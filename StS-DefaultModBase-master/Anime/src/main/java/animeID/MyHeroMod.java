package animeID;

import animeID.cards.CommonPowers.Momo;
import animeID.cards.CommonPowers.Tsuyu;
import animeID.cards.CommonSkills.*;
import animeID.cards.CommonAttacks.*;
import animeID.cards.DefendMH;
import animeID.cards.RareSkills.ProminenceBurn;
import animeID.cards.RareAttacks.USA_Smash;
import animeID.cards.RareSkills.*;
import animeID.cards.RarePowers.*;
import animeID.cards.Smash;
import animeID.cards.UncommonAttacks.*;
import animeID.cards.UncommonPowers.Crystallize;
import animeID.cards.UncommonPowers.Dabi;
import animeID.cards.UncommonPowers.GentleCriminal;
import animeID.cards.UncommonSkills.Hatsume;
import animeID.cards.UncommonPowers.Rei;
import animeID.cards.UncommonSkills.*;
import animeID.relics.HeroCostume;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import animeID.characters.TheDefault;
import animeID.util.IDCheckDontTouchPls;
import animeID.util.TextureLoader;
import animeID.variables.DefaultCustomVariable;
import animeID.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@SpireInitializer
public class MyHeroMod implements
        AddAudioSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public void receiveAddAudio() {
        BaseMod.addAudio("animeID:PlusUltra", "C:/spiremodattempt1/StS-DefaultModBase-master/StS-DefaultModBase-master/Anime/src/main/resources/audio/plusultra.ogg");
        BaseMod.addAudio("animeID:ProminenceBurn", "C:/spiremodattempt1/StS-DefaultModBase-master/StS-DefaultModBase-master/Anime/src/main/resources/audio/ProminenceBurn.ogg");
        BaseMod.addAudio("animeID:USA_Smash", "C:/spiremodattempt1/StS-DefaultModBase-master/StS-DefaultModBase-master/Anime/src/main/resources/audio/USA_Smash.ogg");
    }
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(MyHeroMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "TheHero";
    private static final String AUTHOR = "PC3989";
    private static final String DESCRIPTION = ":)";
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);
    
    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_GRAY = "animeIDResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "animeIDResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "animeIDResources/images/512/bg_power_default_gray.png";
    
    private static final String ENERGY_ORB_DEFAULT_GRAY = "animeIDResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "animeIDResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "animeIDResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "animeIDResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "animeIDResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "animeIDResources/images/1024/card_default_gray_orb.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "animeIDResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "animeIDResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "animeIDResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "animeIDResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "animeIDResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "animeIDResources/images/Badge.png";
    
    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "animeIDResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "animeIDResources/images/char/defaultCharacter/skeleton.json";
    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================
    
    public MyHeroMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);

        setModID("animeID");

        logger.info("Done subscribing");
        
        logger.info("Creating the color " + TheDefault.Enums.COLOR_GRAY.toString());
        
        BaseMod.addColor(TheDefault.Enums.COLOR_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA =====
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson();
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); //
        InputStream in = MyHeroMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8));
        InputStream in = MyHeroMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = MyHeroMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }
    // ====== YOU CAN EDIT AGAIN ======

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        MyHeroMod defaultmod = new MyHeroMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheDefault.Enums.THE_DEFAULT.toString());
        
        BaseMod.addCharacter(new TheDefault("TheHero", TheDefault.Enums.THE_DEFAULT),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, TheDefault.Enums.THE_DEFAULT);
        
        receiveEditPotions();
        logger.info("Added " + TheDefault.Enums.THE_DEFAULT.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("Checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:
            
            enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        //BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================

    // ================ ADD POTIONS ===================
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheDefault.Enums.THE_DEFAULT);
        
        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================
    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        //BaseMod.addRelicToCustomPool(new PlaceholderRelic(), TheDefault.Enums.COLOR_GRAY);
        //BaseMod.addRelicToCustomPool(new BottledPlaceholderRelic(), TheDefault.Enums.COLOR_GRAY);
        //BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), TheDefault.Enums.COLOR_GRAY);
        BaseMod.addRelicToCustomPool(new HeroCostume(), TheDefault.Enums.COLOR_GRAY);


        // This adds a relic to the Shared pool. Every character can find this relic.
        //BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        
        // Mark relics as seen (the others are all starters so they're marked as seen in the character file

        //UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        logger.info("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");
        //You need 1 of each type and rarity (technically) for  game not to crash
        // when generating card rewards/shop screen items.
        
       // BaseMod.addCard(new OrbSkill());
       // BaseMod.addCard(new DefaultSecondMagicNumberSkill());
       // BaseMod.addCard(new DefaultCommonAttack());
       // BaseMod.addCard(new DefaultAttackWithVariable());
       // BaseMod.addCard(new DefaultCommonSkill());
       // BaseMod.addCard(new DefaultCommonPower());
       // BaseMod.addCard(new DefaultUncommonSkill());
       // BaseMod.addCard(new DefaultUncommonAttack());
       // BaseMod.addCard(new DefaultUncommonPower());
       // BaseMod.addCard(new DefaultRareAttack());
       // BaseMod.addCard(new DefaultRareSkill());
       // BaseMod.addCard(new DefaultRarePower());
        BaseMod.addCard(new TodorokiShoto());
        BaseMod.addCard(new RedRiot());
        BaseMod.addCard(new AllMight());
        BaseMod.addCard(new Endeavor());
        BaseMod.addCard(new Deku());
        BaseMod.addCard(new MirioTogata());
        BaseMod.addCard(new ToruHagakure());
        BaseMod.addCard(new PlusUltra());
        BaseMod.addCard(new Mezo());
        BaseMod.addCard(new Uraraka());
        BaseMod.addCard(new Mineta());
        BaseMod.addCard(new Ojiro());
        BaseMod.addCard(new Kaminari());
        BaseMod.addCard(new Shigaraki());
        BaseMod.addCard(new Tsuyu());
        BaseMod.addCard(new Momo());
        BaseMod.addCard(new Bakugo());
        BaseMod.addCard(new EraserHead());
        BaseMod.addCard(new Chisaki());
        BaseMod.addCard(new Twice());
        BaseMod.addCard(new USA_Smash());
        BaseMod.addCard(new ProminenceBurn());
        BaseMod.addCard(new SkinnyGum());
        BaseMod.addCard(new FatGum());
        BaseMod.addCard(new Aoyama());
        BaseMod.addCard(new Hatsume());
        BaseMod.addCard(new Mina());
        BaseMod.addCard(new Rei());
        BaseMod.addCard(new Dabi());
        BaseMod.addCard(new TetsuTetsu());
        BaseMod.addCard(new Kendo());
        BaseMod.addCard(new Muscular());
        BaseMod.addCard(new Nomu());
        BaseMod.addCard(new Rappa());
        BaseMod.addCard(new Kirishima());
        BaseMod.addCard(new Eri());
        BaseMod.addCard(new HowitzerImpact());
        BaseMod.addCard(new NightEye());
        BaseMod.addCard(new Tengai());
        BaseMod.addCard(new RecoveryGirl());
        BaseMod.addCard(new Cementoss());
        BaseMod.addCard(new Himiko());
        BaseMod.addCard(new GentleCriminal());
        BaseMod.addCard(new MtLady());
        BaseMod.addCard(new PresentMic());
        BaseMod.addCard(new Crystallize());
        BaseMod.addCard(new Smash());
        BaseMod.addCard(new DefendMH());
        BaseMod.addCard(new Iida());
        BaseMod.addCard(new Thirteen());
        BaseMod.addCard(new Hawks());
        BaseMod.addCard(new Tamaki());
        BaseMod.addCard(new Kurono());
        BaseMod.addCard(new GranTorino());
        BaseMod.addCard(new ShootStyle());
        BaseMod.addCard(new Stain());

        logger.info("Making sure the cards are unlocked.");
        //UnlockTracker.unlockCard(OrbSkill.ID);
        //UnlockTracker.unlockCard(DefaultSecondMagicNumberSkill.ID);
        //UnlockTracker.unlockCard(DefaultCommonAttack.ID);
       // UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
       // UnlockTracker.unlockCard(DefaultCommonSkill.ID);
       // UnlockTracker.unlockCard(DefaultCommonPower.ID);
       // UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
       // UnlockTracker.unlockCard(DefaultUncommonAttack.ID);
       // UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        //UnlockTracker.unlockCard(DefaultRareAttack.ID);
       // UnlockTracker.unlockCard(DefaultRareSkill.ID);
       // UnlockTracker.unlockCard(DefaultRarePower.ID);


        UnlockTracker.unlockCard(TodorokiShoto.ID);
        UnlockTracker.unlockCard(RedRiot.ID);
        UnlockTracker.unlockCard(AllMight.ID);
        UnlockTracker.unlockCard(Endeavor.ID);
        UnlockTracker.unlockCard(Deku.ID);
        UnlockTracker.unlockCard(MirioTogata.ID);
        UnlockTracker.unlockCard(PlusUltra.ID);
        UnlockTracker.unlockCard(ToruHagakure.ID);
        UnlockTracker.unlockCard(Mineta.ID);
        UnlockTracker.unlockCard(Kaminari.ID);
        UnlockTracker.unlockCard(Uraraka.ID);
        UnlockTracker.unlockCard(Ojiro.ID);
        UnlockTracker.unlockCard(Mezo.ID);
        UnlockTracker.unlockCard(Shigaraki.ID);
        UnlockTracker.unlockCard(Momo.ID);
        UnlockTracker.unlockCard(Tsuyu.ID);
        UnlockTracker.unlockCard(Bakugo.ID);
        UnlockTracker.unlockCard(EraserHead.ID);
        UnlockTracker.unlockCard(Chisaki.ID);
        UnlockTracker.unlockCard(Twice.ID);
        UnlockTracker.unlockCard(ProminenceBurn.ID);
        UnlockTracker.unlockCard(USA_Smash.ID);
        UnlockTracker.unlockCard(SkinnyGum.ID);
        UnlockTracker.unlockCard(FatGum.ID);
        UnlockTracker.unlockCard(Aoyama.ID);
        UnlockTracker.unlockCard(Mineta.ID);
        UnlockTracker.unlockCard(Hatsume.ID);
        UnlockTracker.unlockCard(Rei.ID);
        UnlockTracker.unlockCard(Dabi.ID);
        UnlockTracker.unlockCard(Kendo.ID);
        UnlockTracker.unlockCard(TetsuTetsu.ID);
        UnlockTracker.unlockCard(Muscular.ID);
        UnlockTracker.unlockCard(Nomu.ID);
        UnlockTracker.unlockCard(Rappa.ID);
        UnlockTracker.unlockCard(Kirishima.ID);
        UnlockTracker.unlockCard(Eri.ID);
        UnlockTracker.unlockCard(HowitzerImpact.ID);
        UnlockTracker.unlockCard(NightEye.ID);
        UnlockTracker.unlockCard(RecoveryGirl.ID);
        UnlockTracker.unlockCard(Tengai.ID);
        UnlockTracker.unlockCard(Cementoss.ID);
        UnlockTracker.unlockCard(Himiko.ID);
        UnlockTracker.unlockCard(GentleCriminal.ID);
        UnlockTracker.unlockCard(MtLady.ID);
        UnlockTracker.unlockCard(PresentMic.ID);
        UnlockTracker.unlockCard(Crystallize.ID);
        UnlockTracker.unlockCard(Smash.ID);
        UnlockTracker.unlockCard(DefendMH.ID);
        UnlockTracker.unlockCard(Iida.ID);
        UnlockTracker.unlockCard(Thirteen.ID);
        UnlockTracker.unlockCard(Hawks.ID);
        UnlockTracker.unlockCard(Tamaki.ID);
        UnlockTracker.unlockCard(Kurono.ID);
        UnlockTracker.unlockCard(ShootStyle.ID);
        UnlockTracker.unlockCard(GranTorino.ID);
        UnlockTracker.unlockCard(Stain.ID);



        logger.info("Done adding cards!");
    }

    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Card-Strings.json");
        
        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Power-Strings.json");
        
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Relic-Strings.json");
        
        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Event-Strings.json");
        
        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Potion-Strings.json");
        
        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Character-Strings.json");
        
        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Orb-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    // ================ /LOAD THE TEXT/ ===================
    
    // ================ LOAD THE KEYWORDS ===================
    
    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/DefaultMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
