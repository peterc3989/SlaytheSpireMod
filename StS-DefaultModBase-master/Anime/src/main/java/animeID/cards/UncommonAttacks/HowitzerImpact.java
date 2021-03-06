package animeID.cards.UncommonAttacks;

import animeID.MyHeroMod;
import animeID.actions.HowitzerImpactAction;
import animeID.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import animeID.characters.TheDefault;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

import static animeID.MyHeroMod.makeCardPath;

public class HowitzerImpact extends AbstractDynamicCard {

    public static final String ID = MyHeroMod.makeID(HowitzerImpact.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("HowitzerImpact.png");//


    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3 ;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 25;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/

    public HowitzerImpact() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }
        AbstractDungeon.actionManager.addToBottom(
                new HowitzerImpactAction(p,m,damage,damageTypeForTurn));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
