package animeID.cards.RarePowers;

import animeID.MyHeroMod;
import animeID.cards.AbstractDynamicCard;
import animeID.powers.ShigarakiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import animeID.characters.TheDefault;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.sun.tools.javac.util.List;

import java.util.Arrays;
import java.util.LinkedList;

import static animeID.MyHeroMod.makeCardPath;

public class Shigaraki extends AbstractDynamicCard {

    public static final String ID = MyHeroMod.makeID(Shigaraki.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Shigaraki.png");//


    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;


    // /STAT DECLARATION/

    public Shigaraki() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ShigarakiPower(p,p, magicNumber)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}


