package animeID.actions;

import animeID.cards.CommonAttacks.Bakugo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import animeID.powers.CommonPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

import java.util.Iterator;

public class StainAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private boolean upgraded;
    private int damage;
    public StainAction(final AbstractPlayer p, final AbstractMonster m,int damage,
                        final DamageInfo.DamageType damageTypeForTurn){
        this.p = p;
        this.m=m;
        this.damageType = damageTypeForTurn;
        this.damage=damage;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (m != null) {
                if (Settings.FAST_MODE) {
                    this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
                } else {
                    this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
                }
            }
            AbstractCard c;

            Iterator var5 = AbstractDungeon.player.drawPile.group.iterator();

            while (var5.hasNext()) {
                c = (AbstractCard) var5.next();
                if (c.type == AbstractCard.CardType.CURSE){
                    AbstractDungeon.actionManager.addToBottom(
                            new DamageAction(m, new DamageInfo(p, damage, damageType), AttackEffect.NONE));
                    AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,damage));
                    break;
                }

            }
            this.isDone = true;


        }
    }
}