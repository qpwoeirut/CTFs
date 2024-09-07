export memory memory(initial: 258, max: 258);

global g_a:int = 65536;
global g_b:int = 0;
global g_c:int = 0;
global g_d:int = 0;

export table indirect_function_table:funcref(min: 4, max: 4);

data d_0X0x0X0X0X0x0x0xnaninfNANINF(offset: 65536) =
  "-+   0X0x\00-0X+0X 0X-0x+0x 0x\00nan\00inf\00NAN\00INF\00document.getE"
  "lementById('flag').hidden = false;\00document.body.innerHTML = 'data i"
  "ntegrity violated: no flag points for you';\00document.getElementById("
  "'pts').innerHTML = '%lld';\00.\00(null)\00document.getElementById('%c'"
  ").style.left='%lldpx'\00\00\00\00\00\00\00\00\00\19\00\0b\00\19\19\19\00"
  "\00\00\00\05\00\00\00\00\00\00\09\00\00\00\00\0b\00\00\00\00\00\00\00\00"
  "\19\00\0a\0a\19\19\19\03\0a\07\00\01\00\09\0b\18\00\00\09\06\0b\00\00\0b"
  "\00\06\19\00\00\00\19\19\19\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\0e\00\00\00\00\00\00\00\00\19\00\0b\0d\19\19\19\00\0d\00\00\02\00\09"
  "\0e\00\00\00\09\00\0e\00\00\0e\00\00\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\00\00\00\00\00\00\00\00\00\00\0c\00\00\00\00\00\00\00\00\00\00\00\13"
  "\00\00\00\00\13\00\00\00\00\09\0c\00\00\00\00\00\0c\00\00\0c\00\00\00\00"
  "\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\10\00\00"
  "\00\00\00\00\00\00\00\00\00\0f\00\00\00\04\0f\00\00\00\00\09\10\00\00\00"
  "\00\00\10\00\00\10\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\00\00\00\00\00\00\12\00\00\00\00\00\00\00\00\00\00\00\11\00\00\00\00"
  "\11\00\00\00\00\09\12\00\00\00\00\00\12\00\00\12\00\00\1a\00\00\00\1a\1a"
  "\1a\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\00\1a\00\00\00\1a\1a\1a\00\00\00\00\00\00\09\00\00\00\00\00\00\00\00"
  "\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\00\00\14\00\00\00\00\00\00\00\00\00\00\00\17\00\00\00\00\17\00\00\00"
  "\00\09\14\00\00\00\00\00\14\00\00\14\00\00\00\00\00\00\00\00\00\00\00\00"
  "\00\00\00\00\00\00\00\00\00\00\00\00\00\16\00\00\00\00\00\00\00\00\00\00"
  "\00\15\00\00\00\00\15\00\00\00\00\09\16\00\00\00\00\00\16\00\00\16\00\00"
  "0123456789ABCDEF";
data d_b(offset: 66304) =
  "\15\cd[\07\00\00\00\00\01\00\00\00\00\00\00\00\03\00\00\00\00\00\00\00"
  "\f0\05\01\00";

import function env_emscripten_run_script(a:int);

import function env_emscripten_memcpy_js(a:int, b:int, c:int);

import function env_emscripten_resize_heap(a:int):int;

export function wasm_call_ctors() {
  emscripten_stack_init();
  f_na();
}

export function visit_ad() {
  var a:int = g_a;
  var b:int = 2048;
  var c:int = a - b;
  g_a = c;
  var d:long_ptr = 0;
  var rj:long = d[8292];
  var sj:long = 123456789L;
  var tj:long = rj + sj;
  c[255]:long = tj;
  var e:long_ptr = 0;
  var uj:long = e[8288];
  var vj:long = c[255]:long;
  var f:int = uj != vj;
  var g:int = 1;
  var h:int = f & g;
  if (eqz(h)) goto B_b;
  var i:int = 65629;
  env_emscripten_run_script(i);
  goto B_a;
  label B_b:
  var j:long_ptr = 0;
  var wj:long = j[8288];
  var xj:long = 1L;
  var yj:long = wj + xj;
  var k:long_ptr = 0;
  k[8288] = yj;
  var l:long_ptr = 0;
  var zj:long = l[8292];
  var ak:long = 3L;
  var bk:long = zj * ak;
  var m:long_ptr = 0;
  var ck:long = m[8292];
  var dk:long = bk * ck;
  var n:long_ptr = 0;
  var ek:long = n[8292];
  var fk:long = 5L;
  var gk:long = ek * fk;
  var hk:long = dk + gk;
  var ik:long = 3L;
  var jk:long = hk + ik;
  var o:long_ptr = 0;
  var kk:long = o[8289];
  var lk:long = kk + jk;
  var p:long_ptr = 0;
  p[8289] = lk;
  var q:long_ptr = 0;
  var mk:long = q[8292];
  var nk:long = 3L;
  var ok:long = mk << nk;
  var r:long_ptr = 0;
  var pk:long = r[8292];
  var qk:long = ok * pk;
  var s:long_ptr = 0;
  var rk:long = s[8292];
  var sk:long = qk * rk;
  var t:long_ptr = 0;
  var tk:long = t[8292];
  var uk:long = 3L;
  var vk:long = tk * uk;
  var u:long_ptr = 0;
  var wk:long = u[8292];
  var xk:long = vk * wk;
  var yk:long = sk + xk;
  var v:long_ptr = 0;
  var zk:long = v[8292];
  var al:long = 3L;
  var bl:long = zk * al;
  var cl:long = yk + bl;
  var dl:long = 8L;
  var el:long = cl + dl;
  var w:long_ptr = 0;
  var fl:long = w[8290];
  var gl:long = fl + el;
  var x:long_ptr = 0;
  x[8290] = gl;
  var y:long_ptr = 0;
  var hl:long = y[8292];
  var il:long = 1L;
  var jl:long = hl + il;
  var z:long_ptr = 0;
  z[8292] = jl;
  var aa:int = 1936;
  var ba:int = c + aa;
  var ca:int = ba;
  var da:long_ptr = 0;
  var kl:long = da[8292];
  c[30]:long = kl;
  var ea:int = 65706;
  var fa:int = 240;
  var ga:int = c + fa;
  f_f(ca, ea, ga);
  var ha:int = 1936;
  var ia:int = c + ha;
  var ja:int = ia;
  env_emscripten_run_script(ja);
  var ka:long_ptr = 0;
  var ll:long = ka[8292];
  var la:long_ptr = 0;
  var ml:long = la[8292];
  var nl:long = ll * ml;
  var ma:long_ptr = 0;
  var ol:long = ma[8292];
  var pl:long = nl * ol;
  var na:long_ptr = 0;
  var ql:long = na[8292];
  var oa:long_ptr = 0;
  var rl:long = oa[8292];
  var sl:long = ql * rl;
  var tl:long = pl + sl;
  var pa:long_ptr = 0;
  var ul:long = pa[8292];
  var vl:long = tl + ul;
  var wl:long = 1L;
  var xl:long = vl + wl;
  c[241]:long = xl;
  var qa:long_ptr = 0;
  var yl:long = qa[8289];
  var zl:long = c[241]:long;
  var ra:int = yl != zl;
  var sa:int = 1;
  var ta:int = ra & sa;
  if (eqz(ta)) goto B_c;
  var ua:int = 65629;
  env_emscripten_run_script(ua);
  goto B_a;
  label B_c:
  var va:long_ptr = 0;
  var am:long = va[8292];
  var bm:long = 1000000000000000000L;
  var wa:int = am == bm;
  var xa:int = 1;
  var ya:int = wa & xa;
  if (eqz(ya)) goto B_a;
  var za:int = 65581;
  env_emscripten_run_script(za);
  var ab:int = 1824;
  var bb:int = c + ab;
  var cb:int = bb;
  var db:long_ptr = 0;
  var cm:long = db[8290];
  var dm:long = 1L;
  var em:long = cm >> dm;
  var fm:long = 511L;
  var gm:long = em & fm;
  var hm:long = 117L;
  var im:long = gm ^ hm;
  var eb:int = i32_wrap_i64(im);
  var fb:int = 24;
  var gb:int = eb << fb;
  var hb:int = gb >> fb;
  var ib:long_ptr = 0;
  var jm:long = ib[8290];
  var km:long = 41L;
  var lm:long = jm >> km;
  var mm:long = 511L;
  var nm:long = lm & mm;
  var om:long = 272L;
  var pm:long = nm ^ om;
  c[1]:long = pm;
  c[0]:int = hb;
  var jb:int = 65766;
  f_f(cb, jb, c);
  var kb:int = 1824;
  var lb:int = c + kb;
  var mb:int = lb;
  env_emscripten_run_script(mb);
  var nb:int = 1712;
  var ob:int = c + nb;
  var pb:int = ob;
  var qb:long_ptr = 0;
  var qm:long = qb[8290];
  var rm:long = 46L;
  var sm:long = qm >> rm;
  var tm:long = 511L;
  var um:long = sm & tm;
  var vm:long = 455L;
  var wm:long = um ^ vm;
  var rb:int = i32_wrap_i64(wm);
  var sb:int = 24;
  var tb:int = rb << sb;
  var ub:int = tb >> sb;
  var vb:long_ptr = 0;
  var xm:long = vb[8290];
  var ym:long = 43L;
  var zm:long = xm >> ym;
  var an:long = 511L;
  var bn:long = zm & an;
  var cn:long = 324L;
  var dn:long = bn ^ cn;
  c[3]:long = dn;
  c[4]:int = ub;
  var wb:int = 65766;
  var xb:int = 16;
  var yb:int = c + xb;
  f_f(pb, wb, yb);
  var zb:int = 1712;
  var ac:int = c + zb;
  var bc:int = ac;
  env_emscripten_run_script(bc);
  var cc:int = 1600;
  var dc:int = c + cc;
  var ec:int = dc;
  var fc:long_ptr = 0;
  var en:long = fc[8290];
  var fn:long = 9L;
  var gn:long = en >> fn;
  var hn:long = 511L;
  var in:long = gn & hn;
  var jn:long = 105L;
  var kn:long = in ^ jn;
  var gc:int = i32_wrap_i64(kn);
  var hc:int = 24;
  var ic:int = gc << hc;
  var jc:int = ic >> hc;
  var kc:long_ptr = 0;
  var ln:long = kc[8290];
  var mn:long = 36L;
  var nn:long = ln >> mn;
  var on:long = 511L;
  var pn:long = nn & on;
  var qn:long = 305L;
  var rn:long = pn ^ qn;
  c[5]:long = rn;
  c[8]:int = jc;
  var lc:int = 65766;
  var mc:int = 32;
  var nc:int = c + mc;
  f_f(ec, lc, nc);
  var oc:int = 1600;
  var pc:int = c + oc;
  var qc:int = pc;
  env_emscripten_run_script(qc);
  var rc:int = 1488;
  var sc:int = c + rc;
  var tc:int = sc;
  var uc:long_ptr = 0;
  var sn:long = uc[8290];
  var tn:long = 47L;
  var un:long = sn >> tn;
  var vn:long = 511L;
  var wn:long = un & vn;
  var xn:long = 423L;
  var yn:long = wn ^ xn;
  var vc:int = i32_wrap_i64(yn);
  var wc:int = 24;
  var xc:int = vc << wc;
  var yc:int = xc >> wc;
  var zc:long_ptr = 0;
  var zn:long = zc[8290];
  var ao:long = 28L;
  var bo:long = zn >> ao;
  var co:long = 511L;
  var do:long = bo & co;
  var eo:long = 30L;
  var fo:long = do ^ eo;
  c[7]:long = fo;
  c[12]:int = yc;
  var ad:int = 65766;
  var bd:int = 48;
  var cd:int = c + bd;
  f_f(tc, ad, cd);
  var dd:int = 1488;
  var ed:int = c + dd;
  var fd:int = ed;
  env_emscripten_run_script(fd);
  var gd:int = 1376;
  var hd:int = c + gd;
  var id:int = hd;
  var jd:long_ptr = 0;
  var go:long = jd[8290];
  var ho:long = 18L;
  var io:long = go >> ho;
  var jo:long = 511L;
  var ko:long = io & jo;
  var lo:long = 130L;
  var mo:long = ko ^ lo;
  var kd:int = i32_wrap_i64(mo);
  var ld:int = 24;
  var md:int = kd << ld;
  var nd:int = md >> ld;
  var od:long_ptr = 0;
  var no:long = od[8290];
  var oo:long = 5L;
  var po:long = no >> oo;
  var qo:long = 511L;
  var ro:long = po & qo;
  var so:long = 210L;
  var to:long = ro ^ so;
  c[9]:long = to;
  c[16]:int = nd;
  var pd:int = 65766;
  var qd:int = 64;
  var rd:int = c + qd;
  f_f(id, pd, rd);
  var sd:int = 1376;
  var td:int = c + sd;
  var ud:int = td;
  env_emscripten_run_script(ud);
  var vd:int = 1264;
  var wd:int = c + vd;
  var xd:int = wd;
  var yd:long_ptr = 0;
  var uo:long = yd[8290];
  var vo:long = 23L;
  var wo:long = uo >> vo;
  var xo:long = 511L;
  var yo:long = wo & xo;
  var zo:long = 262L;
  var ap:long = yo ^ zo;
  var zd:int = i32_wrap_i64(ap);
  var ae:int = 24;
  var be:int = zd << ae;
  var ce:int = be >> ae;
  var de:long_ptr = 0;
  var bp:long = de[8290];
  var cp:long = 23L;
  var dp:long = bp >> cp;
  var ep:long = 511L;
  var fp:long = dp & ep;
  var gp:long = 11L;
  var hp:long = fp ^ gp;
  c[11]:long = hp;
  c[20]:int = ce;
  var ee:int = 65766;
  var fe:int = 80;
  var ge:int = c + fe;
  f_f(xd, ee, ge);
  var he:int = 1264;
  var ie:int = c + he;
  var je:int = ie;
  env_emscripten_run_script(je);
  var ke:int = 1152;
  var le:int = c + ke;
  var me:int = le;
  var ne:long_ptr = 0;
  var ip:long = ne[8290];
  var jp:long = 46L;
  var kp:long = ip >> jp;
  var lp:long = 511L;
  var mp:long = kp & lp;
  var np:long = 453L;
  var op:long = mp ^ np;
  var oe:int = i32_wrap_i64(op);
  var pe:int = 24;
  var qe:int = oe << pe;
  var re:int = qe >> pe;
  var se:long_ptr = 0;
  var pp:long = se[8290];
  var qp:long = 28L;
  var rp:long = pp >> qp;
  var sp:long = 511L;
  var tp:long = rp & sp;
  var up:long = 45L;
  var vp:long = tp ^ up;
  c[13]:long = vp;
  c[24]:int = re;
  var te:int = 65766;
  var ue:int = 96;
  var ve:int = c + ue;
  f_f(me, te, ve);
  var we:int = 1152;
  var xe:int = c + we;
  var ye:int = xe;
  env_emscripten_run_script(ye);
  var ze:int = 1040;
  var af:int = c + ze;
  var bf:int = af;
  var cf:long_ptr = 0;
  var wp:long = cf[8290];
  var xp:long = 54L;
  var yp:long = wp >> xp;
  var zp:long = 511L;
  var aq:long = yp & zp;
  var bq:long = 45L;
  var cq:long = aq ^ bq;
  var df:int = i32_wrap_i64(cq);
  var ef:int = 24;
  var ff:int = df << ef;
  var gf:int = ff >> ef;
  var hf:long_ptr = 0;
  var dq:long = hf[8290];
  var eq:long = 35L;
  var fq:long = dq >> eq;
  var gq:long = 511L;
  var hq:long = fq & gq;
  var iq:long = 337L;
  var jq:long = hq ^ iq;
  c[15]:long = jq;
  c[28]:int = gf;
  var if:int = 65766;
  var jf:int = 112;
  var kf:int = c + jf;
  f_f(bf, if, kf);
  var lf:int = 1040;
  var mf:int = c + lf;
  var nf:int = mf;
  env_emscripten_run_script(nf);
  var of:int = 928;
  var pf:int = c + of;
  var qf:int = pf;
  var rf:long_ptr = 0;
  var kq:long = rf[8290];
  var lq:long = 51L;
  var mq:long = kq >> lq;
  var nq:long = 511L;
  var oq:long = mq & nq;
  var pq:long = 108L;
  var qq:long = oq ^ pq;
  var sf:int = i32_wrap_i64(qq);
  var tf:int = 24;
  var uf:int = sf << tf;
  var vf:int = uf >> tf;
  var wf:long_ptr = 0;
  var rq:long = wf[8290];
  var sq:long = 1L;
  var tq:long = rq >> sq;
  var uq:long = 511L;
  var vq:long = tq & uq;
  var wq:long = 104L;
  var xq:long = vq ^ wq;
  c[17]:long = xq;
  c[32]:int = vf;
  var xf:int = 65766;
  var yf:int = 128;
  var zf:int = c + yf;
  f_f(qf, xf, zf);
  var ag:int = 928;
  var bg:int = c + ag;
  var cg:int = bg;
  env_emscripten_run_script(cg);
  var dg:int = 816;
  var eg:int = c + dg;
  var fg:int = eg;
  var gg:long_ptr = 0;
  var yq:long = gg[8290];
  var zq:long = 39L;
  var ar:long = yq >> zq;
  var br:long = 511L;
  var cr:long = ar & br;
  var dr:long = 15L;
  var er:long = cr ^ dr;
  var hg:int = i32_wrap_i64(er);
  var ig:int = 24;
  var jg:int = hg << ig;
  var kg:int = jg >> ig;
  var lg:long_ptr = 0;
  var fr:long = lg[8290];
  var gr:long = 52L;
  var hr:long = fr >> gr;
  var ir:long = 511L;
  var jr:long = hr & ir;
  var kr:long = 496L;
  var lr:long = jr ^ kr;
  c[19]:long = lr;
  c[36]:int = kg;
  var mg:int = 65766;
  var ng:int = 144;
  var og:int = c + ng;
  f_f(fg, mg, og);
  var pg:int = 816;
  var qg:int = c + pg;
  var rg:int = qg;
  env_emscripten_run_script(rg);
  var sg:int = 704;
  var tg:int = c + sg;
  var ug:int = tg;
  var vg:long_ptr = 0;
  var mr:long = vg[8290];
  var nr:long = 30L;
  var or:long = mr >> nr;
  var pr:long = 511L;
  var qr:long = or & pr;
  var rr:long = 22L;
  var sr:long = qr ^ rr;
  var wg:int = i32_wrap_i64(sr);
  var xg:int = 24;
  var yg:int = wg << xg;
  var zg:int = yg >> xg;
  var ah:long_ptr = 0;
  var tr:long = ah[8290];
  var ur:long = 43L;
  var vr:long = tr >> ur;
  var wr:long = 511L;
  var xr:long = vr & wr;
  var yr:long = 511L;
  var zr:long = xr ^ yr;
  c[21]:long = zr;
  c[40]:int = zg;
  var bh:int = 65766;
  var ch:int = 160;
  var dh:int = c + ch;
  f_f(ug, bh, dh);
  var eh:int = 704;
  var fh:int = c + eh;
  var gh:int = fh;
  env_emscripten_run_script(gh);
  var hh:int = 592;
  var ih:int = c + hh;
  var jh:int = ih;
  var kh:long_ptr = 0;
  var as:long = kh[8290];
  var bs:long = 46L;
  var cs:long = as >> bs;
  var ds:long = 511L;
  var es:long = cs & ds;
  var fs:long = 452L;
  var gs:long = es ^ fs;
  var lh:int = i32_wrap_i64(gs);
  var mh:int = 24;
  var nh:int = lh << mh;
  var oh:int = nh >> mh;
  var ph:long_ptr = 0;
  var hs:long = ph[8290];
  var is:long = 41L;
  var js:long = hs >> is;
  var ks:long = 511L;
  var ls:long = js & ks;
  var ms:long = 187L;
  var ns:long = ls ^ ms;
  c[23]:long = ns;
  c[44]:int = oh;
  var qh:int = 65766;
  var rh:int = 176;
  var sh:int = c + rh;
  f_f(jh, qh, sh);
  var th:int = 592;
  var uh:int = c + th;
  var vh:int = uh;
  env_emscripten_run_script(vh);
  var wh:int = 480;
  var xh:int = c + wh;
  var yh:int = xh;
  var zh:long_ptr = 0;
  var os:long = zh[8290];
  var ps:long = 23L;
  var qs:long = os >> ps;
  var rs:long = 511L;
  var ss:long = qs & rs;
  var ts:long = 322L;
  var us:long = ss ^ ts;
  var ai:int = i32_wrap_i64(us);
  var bi:int = 24;
  var ci:int = ai << bi;
  var di:int = ci >> bi;
  var ei:long_ptr = 0;
  var vs:long = ei[8290];
  var ws:long = 19L;
  var xs:long = vs >> ws;
  var ys:long = 511L;
  var zs:long = xs & ys;
  var at:long = 362L;
  var bt:long = zs ^ at;
  c[25]:long = bt;
  c[48]:int = di;
  var fi:int = 65766;
  var gi:int = 192;
  var hi:int = c + gi;
  f_f(yh, fi, hi);
  var ii:int = 480;
  var ji:int = c + ii;
  var ki:int = ji;
  env_emscripten_run_script(ki);
  var li:int = 368;
  var mi:int = c + li;
  var ni:int = mi;
  var oi:long_ptr = 0;
  var ct:long = oi[8290];
  var dt:long = 11L;
  var et:long = ct >> dt;
  var ft:long = 511L;
  var gt:long = et & ft;
  var ht:long = 492L;
  var it:long = gt ^ ht;
  var pi:int = i32_wrap_i64(it);
  var qi:int = 24;
  var ri:int = pi << qi;
  var si:int = ri >> qi;
  var ti:long_ptr = 0;
  var jt:long = ti[8290];
  var kt:long = 52L;
  var lt:long = jt >> kt;
  var mt:long = 511L;
  var nt:long = lt & mt;
  var ot:long = 409L;
  var pt:long = nt ^ ot;
  c[27]:long = pt;
  c[52]:int = si;
  var ui:int = 65766;
  var vi:int = 208;
  var wi:int = c + vi;
  f_f(ni, ui, wi);
  var xi:int = 368;
  var yi:int = c + xi;
  var zi:int = yi;
  env_emscripten_run_script(zi);
  var aj:int = 256;
  var bj:int = c + aj;
  var cj:int = bj;
  var dj:long_ptr = 0;
  var qt:long = dj[8290];
  var rt:long = 46L;
  var st:long = qt >> rt;
  var tt:long = 511L;
  var ut:long = st & tt;
  var vt:long = 397L;
  var wt:long = ut ^ vt;
  var ej:int = i32_wrap_i64(wt);
  var fj:int = 24;
  var gj:int = ej << fj;
  var hj:int = gj >> fj;
  var ij:long_ptr = 0;
  var xt:long = ij[8290];
  var yt:long = 24L;
  var zt:long = xt >> yt;
  var au:long = 511L;
  var bu:long = zt & au;
  var cu:long = 168L;
  var du:long = bu ^ cu;
  c[29]:long = du;
  c[56]:int = hj;
  var jj:int = 65766;
  var kj:int = 224;
  var lj:int = c + kj;
  f_f(cj, jj, lj);
  var mj:int = 256;
  var nj:int = c + mj;
  var oj:int = nj;
  env_emscripten_run_script(oj);
  label B_a:
  var pj:int = 2048;
  var qj:int = c + pj;
  g_a = qj;
}

function f_f(a:int, b:int, c:int):int {
  var d:int_ptr = g_a - 16;
  g_a = d;
  d[3] = c;
  c = f_ja(a, b, c);
  g_a = d + 16;
  return c;
}

function f_g(a:{ a:byte, b:byte, c:byte, d:byte }, b:{ a:long, b:long, c:long, d:long }, c:int):int {
  var e:int;
  var f:int;
  if (eqz(c)) goto B_a;
  a.a = b;
  var d:{ a:int, b:int, c:int, d:int, e:int, f:int, g:int } = a + c;
  (d + -1)[0]:byte = b;
  if (c < 3) goto B_a;
  a.c = b;
  a.b = b;
  (d + -3)[0]:byte = b;
  (d + -2)[0]:byte = b;
  if (c < 7) goto B_a;
  a.d = b;
  (d + -4)[0]:byte = b;
  if (c < 9) goto B_a;
  d = a + (e = 0 - a & 3);
  d.a = (b = (b & 255) * 16843009);
  c = d + (e = c - e & -4);
  (c + -4)[0]:int = b;
  if (e < 9) goto B_a;
  d.c = b;
  d.b = b;
  (c + -8)[0]:int = b;
  (c + -12)[0]:int = b;
  if (e < 25) goto B_a;
  d.g = b;
  d.f = b;
  d.e = b;
  d.d = b;
  (c + -16)[0]:int = b;
  (c + -20)[0]:int = b;
  (c + -24)[0]:int = b;
  (c + -28)[0]:int = b;
  c = e - (f = (d & 4) | 24);
  if (c < 32) goto B_a;
  var g:long = i64_extend_i32_u(b) * 4294967297L;
  b = d + f;
  loop L_b {
    b.d = g;
    b.c = g;
    b.b = g;
    b.a = g;
    b = b + 32;
    c = c + -32;
    if (c > 31) continue L_b;
  }
  label B_a:
  return a;
}

function f_h(a:int):int {
  return 1
}

function f_i(a:int) {
}

function f_j(a:int) {
}

function f_k(a:int) {
}

function f_l():int {
  f_j(66344);
  return 66348;
}

function f_m() {
  f_k(66344)
}

function f_n(a:int):int {
  var b:int;
  a[18]:int = (b = a[18]:int) + -1 | b;
  b = a[0]:int;
  if (eqz(b & 8)) goto B_a;
  a[0]:int = b | 32;
  return -1;
  label B_a:
  a[1]:long@4 = 0L;
  a[7]:int = (b = a[11]:int);
  a[5]:int = b;
  a[4]:int = b + a[12]:int;
  return 0;
}

function f_o(a:int, b:int, c:int):int {
  var e:int;
  var d:int = c != 0;
  if (eqz(a & 3)) goto B_c;
  if (eqz(c)) goto B_c;
  e = b & 255;
  loop L_d {
    if (a[0]:ubyte == e) goto B_b;
    c = c + -1;
    d = c != 0;
    a = a + 1;
    if (eqz(a & 3)) goto B_c;
    if (c) continue L_d;
  }
  label B_c:
  if (eqz(d)) goto B_a;
  if (a[0]:ubyte == (b & 255)) goto B_e;
  if (c < 4) goto B_e;
  e = (b & 255) * 16843009;
  loop L_f {
    if (
      ((16843008 - (d = a[0]:int ^ e) | d) & -2139062144) != -2139062144) goto B_b;
    a = a + 4;
    c = c + -4;
    if (c > 3) continue L_f;
  }
  label B_e:
  if (eqz(c)) goto B_a;
  label B_b:
  d = b & 255;
  loop L_g {
    if (a[0]:ubyte != d) goto B_h;
    return a;
    label B_h:
    a = a + 1;
    c = c + -1;
    if (c) continue L_g;
  }
  label B_a:
  return 0;
}

function f_p(a:int, b:int):int {
  var c:int = f_o(a, 0, b);
  return select_if(c - a, b, c);
}

function f_q():int {
  return 66356
}

function f_r(a:double, b:int_ptr):double {
  var c:long = i64_reinterpret_f64(a);
  var d:int = i32_wrap_i64(c >> 52L) & 2047;
  if (d == 2047) goto B_a;
  if (d) goto B_b;
  if (a != 0.0) goto B_d;
  d = 0;
  goto B_c;
  label B_d:
  a = f_r(a * 18446744073709551616.0, b);
  d = b[0] + -64;
  label B_c:
  b[0] = d;
  return a;
  label B_b:
  b[0] = d + -1022;
  a = f64_reinterpret_i64(
        (c & -9218868437227405313L) | 4602678819172646912L);
  label B_a:
  return a;
}

function f_s(a:int, b:int, c:int):int {
  var f:int;
  var e:int;
  if (c < 512) goto B_a;
  env_emscripten_memcpy_js(a, b, c);
  return a;
  label B_a:
  var d:int = a + c;
  if ((b ^ a) & 3) goto B_c;
  if (a & 3) goto B_e;
  c = a;
  goto B_d;
  label B_e:
  if (c) goto B_f;
  c = a;
  goto B_d;
  label B_f:
  c = a;
  loop L_g {
    c[0]:byte = b[0]:ubyte;
    b = b + 1;
    c = c + 1;
    if (eqz(c & 3)) goto B_d;
    if (c < d) continue L_g;
  }
  label B_d:
  e = d & -4;
  if (d < 64) goto B_h;
  if (c > (f = e + -64)) goto B_h;
  loop L_i {
    c[0]:int = b[0]:int;
    c[1]:int = b[1]:int;
    c[2]:int = b[2]:int;
    c[3]:int = b[3]:int;
    c[4]:int = b[4]:int;
    c[5]:int = b[5]:int;
    c[6]:int = b[6]:int;
    c[7]:int = b[7]:int;
    c[8]:int = b[8]:int;
    c[9]:int = b[9]:int;
    c[10]:int = b[10]:int;
    c[11]:int = b[11]:int;
    c[12]:int = b[12]:int;
    c[13]:int = b[13]:int;
    c[14]:int = b[14]:int;
    c[15]:int = b[15]:int;
    b = b + 64;
    c = c + 64;
    if (c <= f) continue L_i;
  }
  label B_h:
  if (c >= e) goto B_b;
  loop L_j {
    c[0]:int = b[0]:int;
    b = b + 4;
    c = c + 4;
    if (c < e) continue L_j;
    goto B_b;
  }
  unreachable;
  label B_c:
  if (d >= 4) goto B_k;
  c = a;
  goto B_b;
  label B_k:
  e = d + -4;
  if (e >= a) goto B_l;
  c = a;
  goto B_b;
  label B_l:
  c = a;
  loop L_m {
    c[0]:byte = b[0]:ubyte;
    c[1]:byte = b[1]:ubyte;
    c[2]:byte = b[2]:ubyte;
    c[3]:byte = b[3]:ubyte;
    b = b + 4;
    c = c + 4;
    if (c <= e) continue L_m;
  }
  label B_b:
  if (c >= d) goto B_n;
  loop L_o {
    c[0]:byte = b[0]:ubyte;
    b = b + 1;
    c = c + 1;
    if (c != d) continue L_o;
  }
  label B_n:
  return a;
}

function f(a:int, b:int, c:int_ptr):int {
  var d:int;
  var e:int;
  var f:int;
  d = c[4];
  if (d) goto B_b;
  e = 0;
  if (f_n(c)) goto B_a;
  d = c[4];
  label B_b:
  if (d - (e = c[5]) >= b) goto B_c;
  return call_indirect(c, a, b, c[9]);
  label B_c:
  if (c[20] < 0) goto B_e;
  if (eqz(b)) goto B_e;
  d = b;
  loop L_g {
    f = a + d;
    if ((f + -1)[0]:ubyte == 10) goto B_f;
    d = d + -1;
    if (eqz(d)) goto B_e;
    continue L_g;
  }
  unreachable;
  label B_f:
  e = call_indirect(c, a, d, c[9]);
  if (e < d) goto B_a;
  b = b - d;
  e = c[5];
  goto B_d;
  label B_e:
  f = a;
  d = 0;
  label B_d:
  f_s(e, f, b);
  c[5] = c[5] + b;
  e = d + b;
  label B_a:
  return e;
}

function f_u(a:int, b:int, c:int, d:int, e:int):int {
  var h:int;
  var i:int;
  var g:int;
  var f:int_ptr = g_a - 208;
  g_a = f;
  f[51] = c;
  f_g(f + 160, 0, 40);
  f[50] = f[51];
  if (f_v(0, b, f + 200, f + 80, f + 160, d, e) >= 0) goto B_b;
  e = -1;
  goto B_a;
  label B_b:
  if (a[19]:int >= 0) goto B_d;
  g = 1;
  goto B_c;
  label B_d:
  g = eqz(f_h(a));
  label B_c:
  a[0]:int = (h = a[0]:int) & -33;
  if (a[12]:int) goto B_h;
  a[12]:int = 80;
  a[7]:int = 0;
  a[2]:long = 0L;
  i = a[11]:int;
  a[11]:int = f;
  goto B_g;
  label B_h:
  i = 0;
  if (a[4]:int) goto B_f;
  label B_g:
  c = -1;
  if (f_n(a)) goto B_e;
  label B_f:
  c = f_v(a, b, f + 200, f + 80, f + 160, d, e);
  label B_e:
  e = h & 32;
  if (eqz(i)) goto B_i;
  call_indirect(a, 0, 0, a[9]:int);
  a[12]:int = 0;
  a[11]:int = i;
  a[7]:int = 0;
  d = a[5]:int;
  a[2]:long = 0L;
  c = select_if(c, -1, d);
  label B_i:
  a[0]:int = (d = a[0]:int) | e;
  e = select_if(-1, c, d & 32);
  if (g) goto B_a;
  f_i(a);
  label B_a:
  g_a = f + 208;
  return e;
}

function f_v(a:ubyte_ptr, b:int, c:int_ptr, d:int, e:int, f:int, g:int):int {
  var p:int;
  var s:byte_ptr;
  var t:int;
  var z:long;
  var v:int;
  var w:ubyte_ptr;
  var o:int;
  var x:int;
  var u:int;
  var q:int;
  var r:int;
  var y:int;
  var h:int = g_a - 64;
  g_a = h;
  h[15]:int = b;
  var i:int = h + 39;
  var j:int = h + 40;
  var k:int = 0;
  var l:int = 0;
  loop L_e {
    var m:int = 0;
    loop L_f {
      var n:ubyte_ptr = b;
      if (m > (l ^ 2147483647)) goto B_d;
      l = m + l;
      m = n;
      o = n[0];
      if (eqz(o)) goto B_l;
      loop L_m {
        o = o & 255;
        if (o) goto B_p;
        b = m;
        goto B_o;
        label B_p:
        if (o != 37) goto B_n;
        o = m;
        loop L_q {
          if (o[1]:ubyte == 37) goto B_r;
          b = o;
          goto B_o;
          label B_r:
          m = m + 1;
          p = o[2]:ubyte;
          b = o + 2;
          o = b;
          if (p == 37) continue L_q;
        }
        label B_o:
        m = m - n;
        if (m > (o = l ^ 2147483647)) goto B_d;
        if (eqz(a)) goto B_s;
        f_w(a, n, m);
        label B_s:
        if (m) continue L_f;
        h[15]:int = b;
        m = b + 1;
        q = -1;
        p = b[1]:byte + -48;
        if (p > 9) goto B_t;
        if (b[2]:ubyte != 36) goto B_t;
        m = b + 3;
        k = 1;
        q = p;
        label B_t:
        h[15]:int = m;
        r = 0;
        s = m[0]:byte;
        b = s + -32;
        if (b <= 31) goto B_v;
        p = m;
        goto B_u;
        label B_v:
        r = 0;
        p = m;
        b = 1 << b;
        if (eqz(b & 75913)) goto B_u;
        loop L_w {
          h[15]:int = (p = m + 1);
          r = b | r;
          s = m[1]:byte;
          b = s + -32;
          if (b >= 32) goto B_u;
          m = p;
          b = 1 << b;
          if (b & 75913) continue L_w;
        }
        label B_u:
        if (s != 42) goto B_y;
        m = p[1]:byte + -48;
        if (m > 9) goto B_aa;
        if (p[2]:ubyte != 36) goto B_aa;
        if (a) goto B_ca;
        e[m]:int = 10;
        t = 0;
        goto B_ba;
        label B_ca:
        t = (d + (m << 3))[0]:int;
        label B_ba:
        b = p + 3;
        k = 1;
        goto B_z;
        label B_aa:
        if (k) goto B_k;
        b = p + 1;
        if (a) goto B_da;
        h[15]:int = b;
        k = 0;
        t = 0;
        goto B_x;
        label B_da:
        c[0] = (m = c[0]) + 4;
        t = m[0]:int;
        k = 0;
        label B_z:
        h[15]:int = b;
        if (t > -1) goto B_x;
        t = 0 - t;
        r = r | 8192;
        goto B_x;
        label B_y:
        t = f_x(h + 60);
        if (t < 0) goto B_d;
        b = h[15]:int;
        label B_x:
        m = 0;
        u = -1;
        if (b[0]:ubyte == 46) goto B_fa;
        v = 0;
        goto B_ea;
        label B_fa:
        if (b[1]:ubyte != 42) goto B_ga;
        p = b[2]:byte + -48;
        if (p > 9) goto B_ia;
        if (b[3]:ubyte != 36) goto B_ia;
        if (a) goto B_ka;
        e[p]:int = 10;
        u = 0;
        goto B_ja;
        label B_ka:
        u = (d + (p << 3))[0]:int;
        label B_ja:
        b = b + 4;
        goto B_ha;
        label B_ia:
        if (k) goto B_k;
        b = b + 2;
        if (a) goto B_la;
        u = 0;
        goto B_ha;
        label B_la:
        c[0] = (p = c[0]) + 4;
        u = p[0]:int;
        label B_ha:
        h[15]:int = b;
        v = u > -1;
        goto B_ea;
        label B_ga:
        h[15]:int = b + 1;
        v = 1;
        u = f_x(h + 60);
        b = h[15]:int;
        label B_ea:
        loop L_ma {
          p = m;
          w = 28;
          s = b;
          m = s[0];
          if (m + -123 < -58) goto B_c;
          b = s + 1;
          m = (m + p * 58 + 65759)[0]:ubyte;
          if (m + -1 < 8) continue L_ma;
        }
        h[15]:int = b;
        if (m == 27) goto B_oa;
        if (eqz(m)) goto B_c;
        if (q < 0) goto B_pa;
        if (a) goto B_qa;
        e[q]:int = m;
        continue L_e;
        label B_qa:
        h[6]:long = d[q]:long;
        goto B_na;
        label B_pa:
        if (eqz(a)) goto B_g;
        f_y(h + 48, m, c, g);
        goto B_na;
        label B_oa:
        if (q > -1) goto B_c;
        m = 0;
        if (eqz(a)) continue L_f;
        label B_na:
        if (a[0] & 32) goto B_b;
        x = r & -65537;
        r = select_if(x, r, r & 8192);
        q = 0;
        y = 65536;
        w = j;
        m = s[0];
        m = select_if(select_if(m & -45, m, (m & 15) == 3), m, p);
        br_table[B_db, B_h, B_h, B_h, B_h, B_h, B_h, B_h, B_h, B_ra, B_h, B_ya, B_bb, B_ra, B_ra, B_ra, B_h, B_bb, B_h, B_h, B_h, B_h, B_fb, B_cb, B_eb, B_h, B_h, B_xa, B_h, B_gb, B_h, B_h, B_db, ..B_hb](
          m + -88);
        label B_hb:
        w = j;
        br_table[B_ra, B_h, B_wa, B_h, B_ra, B_ra, B_ra, ..B_ib](m + -65)
        label B_ib:
        if (m == 83) goto B_va;
        goto B_i;
        label B_gb:
        q = 0;
        y = 65536;
        z = h[6]:long;
        goto B_ab;
        label B_fb:
        m = 0;
        br_table[B_pb, B_ob, B_nb, B_mb, B_lb, L_f, B_kb, B_jb, ..L_f](
          p & 255)
        label B_pb:
        (h[12]:int)[0]:int = l;
        continue L_f;
        label B_ob:
        (h[12]:int)[0]:int = l;
        continue L_f;
        label B_nb:
        (h[12]:int)[0]:long = i64_extend_i32_s(l);
        continue L_f;
        label B_mb:
        (h[12]:int)[0]:short = l;
        continue L_f;
        label B_lb:
        (h[12]:int)[0]:byte = l;
        continue L_f;
        label B_kb:
        (h[12]:int)[0]:int = l;
        continue L_f;
        label B_jb:
        (h[12]:int)[0]:long = i64_extend_i32_s(l);
        continue L_f;
        label B_eb:
        u = select_if(u, 8, u > 8);
        r = r | 8;
        m = 120;
        label B_db:
        n = f_z(h[6]:long, j, m & 32);
        q = 0;
        y = 65536;
        if (eqz(h[6]:long)) goto B_za;
        if (eqz(r & 8)) goto B_za;
        y = (m >> 4) + 65536;
        q = 2;
        goto B_za;
        label B_cb:
        q = 0;
        y = 65536;
        n = f_aa(h[6]:long, j);
        if (eqz(r & 8)) goto B_za;
        u = select_if(u, (m = j - n) + 1, u > m);
        goto B_za;
        label B_bb:
        z = h[6]:long;
        if (z > -1L) goto B_qb;
        h[6]:long = (z = 0L - z);
        q = 1;
        y = 65536;
        goto B_ab;
        label B_qb:
        if (eqz(r & 2048)) goto B_rb;
        q = 1;
        y = 65537;
        goto B_ab;
        label B_rb:
        y = select_if(65538, 65536, q = r & 1);
        label B_ab:
        n = f_ba(z, j);
        label B_za:
        if (v & u < 0) goto B_d;
        r = select_if(r & -65537, r, v);
        z = h[6]:long;
        if (z != 0L) goto B_sb;
        if (u) goto B_sb;
        n = j;
        w = j;
        u = 0;
        goto B_h;
        label B_sb:
        u = select_if(u, m = j - n + eqz(z), u > m);
        goto B_i;
        label B_ya:
        z = h[6]:long;
        goto B_j;
        label B_xa:
        m = h[12]:int;
        n = select_if(m, 65759, m);
        w = n + (m = f_p(n, select_if(u, 2147483647, u < 2147483647)));
        if (u <= -1) goto B_tb;
        r = x;
        u = m;
        goto B_h;
        label B_tb:
        r = x;
        u = m;
        if (w[0]) goto B_d;
        goto B_h;
        label B_wa:
        z = h[6]:long;
        if (eqz(eqz(z))) goto B_ua;
        z = 0L;
        goto B_j;
        label B_va:
        if (eqz(u)) goto B_ub;
        o = h[12]:int;
        goto B_ta;
        label B_ub:
        m = 0;
        f_ca(a, 32, t, 0, r);
        goto B_sa;
        label B_ua:
        h[3]:int = 0;
        h[2]:int = z;
        h[12]:int = h + 8;
        o = h + 8;
        u = -1;
        label B_ta:
        m = 0;
        loop L_wb {
          p = o[0]:int;
          if (eqz(p)) goto B_vb;
          p = f_pa(h + 4, p);
          if (p < 0) goto B_b;
          if (p > u - m) goto B_vb;
          o = o + 4;
          m = p + m;
          if (m < u) continue L_wb;
        }
        label B_vb:
        w = 61;
        if (m < 0) goto B_c;
        f_ca(a, 32, t, m, r);
        if (m) goto B_xb;
        m = 0;
        goto B_sa;
        label B_xb:
        p = 0;
        o = h[12]:int;
        loop L_yb {
          n = o[0]:int;
          if (eqz(n)) goto B_sa;
          n = f_pa(h + 4, n);
          p = n + p;
          if (p > m) goto B_sa;
          f_w(a, h + 4, n);
          o = o + 4;
          if (p < m) continue L_yb;
        }
        label B_sa:
        f_ca(a, 32, t, m, r ^ 8192);
        m = select_if(t, m, t > m);
        continue L_f;
        label B_ra:
        if (v & u < 0) goto B_d;
        w = 61;
        m = call_indirect(a, h[6]:double, t, u, r, m, f);
        if (m >= 0) continue L_f;
        goto B_c;
        label B_n:
        o = m[1]:ubyte;
        m = m + 1;
        continue L_m;
      }
      unreachable;
      label B_l:
      if (a) goto B_a;
      if (eqz(k)) goto B_g;
      m = 1;
      loop L_ac {
        o = e[m]:int;
        if (eqz(o)) goto B_zb;
        f_y(d + (m << 3), o, c, g);
        l = 1;
        m = m + 1;
        if (m != 10) continue L_ac;
        goto B_a;
      }
      unreachable;
      label B_zb:
      if (m < 10) goto B_bc;
      l = 1;
      goto B_a;
      label B_bc:
      loop L_cc {
        if (e[m]:int) goto B_k;
        l = 1;
        m = m + 1;
        if (m == 10) goto B_a;
        continue L_cc;
      }
      unreachable;
      label B_k:
      w = 28;
      goto B_c;
      label B_j:
      h[39]:byte = z;
      u = 1;
      n = i;
      w = j;
      r = x;
      goto B_h;
      label B_i:
      w = j;
      label B_h:
      s = select_if(u, b = w - n, u > b);
      if (s > (q ^ 2147483647)) goto B_d;
      w = 61;
      m = select_if(t, p = q + s, t > p);
      if (m > o) goto B_c;
      f_ca(a, 32, m, p, r);
      f_w(a, y, q);
      f_ca(a, 48, m, p, r ^ 65536);
      f_ca(a, 48, s, b, 0);
      f_w(a, n, b);
      f_ca(a, 32, m, p, r ^ 8192);
      b = h[15]:int;
      continue L_f;
      label B_g:
    }
  }
  l = 0;
  goto B_a;
  label B_d:
  w = 61;
  label B_c:
  f_q()[0]:int = w;
  label B_b:
  l = -1;
  label B_a:
  g_a = h + 64;
  return l;
}

function f_w(a:ubyte_ptr, b:int, c:int) {
  if (a[0] & 32) goto B_a;
  f(b, c, a);
  label B_a:
}

function f_x(a:int_ptr):int {
  var d:int;
  var c:{ a:byte, b:byte }
  var e:int;
  var b:int = 0;
  c = a[0];
  d = c.a + -48;
  if (d <= 9) goto B_a;
  return 0;
  label B_a:
  loop L_b {
    e = -1;
    if (b > 214748364) goto B_c;
    e = select_if(-1, d + (b = b * 10), d > (b ^ 2147483647));
    label B_c:
    a[0] = (d = c + 1);
    var f:int = c.b;
    b = e;
    c = d;
    d = f + -48;
    if (d < 10) continue L_b;
  }
  return e;
}

function f_y(a:int, b:int, c:int_ptr, d:int) {
  br_table[B_s, B_r, B_q, B_n, B_p, B_o, B_m, B_l, B_k, B_j, B_i, B_h, B_g, B_f, B_e, B_d, B_c, B_b, ..B_a](
    b + -9)
  label B_s:
  c[0] = (b = c[0]) + 4;
  a[0]:int = b[0]:int;
  return ;
  label B_r:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:int;
  return ;
  label B_q:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:uint;
  return ;
  label B_p:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:int;
  return ;
  label B_o:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:uint;
  return ;
  label B_n:
  c[0] = (b = c[0] + 7 & -8) + 8;
  a[0]:long = b[0]:long;
  return ;
  label B_m:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:short;
  return ;
  label B_l:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:ushort;
  return ;
  label B_k:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:byte;
  return ;
  label B_j:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:ubyte;
  return ;
  label B_i:
  c[0] = (b = c[0] + 7 & -8) + 8;
  a[0]:long = b[0]:long;
  return ;
  label B_h:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:uint;
  return ;
  label B_g:
  c[0] = (b = c[0] + 7 & -8) + 8;
  a[0]:long = b[0]:long;
  return ;
  label B_f:
  c[0] = (b = c[0] + 7 & -8) + 8;
  a[0]:long = b[0]:long;
  return ;
  label B_e:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:int;
  return ;
  label B_d:
  c[0] = (b = c[0]) + 4;
  a[0]:long = b[0]:uint;
  return ;
  label B_c:
  c[0] = (b = c[0] + 7 & -8) + 8;
  a[0]:double = b[0]:double;
  return ;
  label B_b:
  call_indirect(a, c, d);
  label B_a:
}

function f_z(a:long, b:byte_ptr, c:int):int {
  if (eqz(a)) goto B_a;
  loop L_b {
    b = b + -1;
    b[0] = ((i32_wrap_i64(a) & 15) + 66288)[0]:ubyte | c;
    var d:int = a > 15L;
    a = a >> 4L;
    if (d) continue L_b;
  }
  label B_a:
  return b;
}

function f_aa(a:long, b:byte_ptr):int {
  if (eqz(a)) goto B_a;
  loop L_b {
    b = b + -1;
    b[0] = (i32_wrap_i64(a) & 7) | 48;
    var c:int = a > 7L;
    a = a >> 3L;
    if (c) continue L_b;
  }
  label B_a:
  return b;
}

function f_ba(a:long, b:byte_ptr):int {
  var c:long;
  var d:int;
  var e:int;
  if (a >= 4294967296L) goto B_b;
  c = a;
  goto B_a;
  label B_b:
  loop L_c {
    b = b + -1;
    b[0] = i32_wrap_i64(a - (c = a / 10L) * 10L) | 48;
    d = a > 42949672959L;
    a = c;
    if (d) continue L_c;
  }
  label B_a:
  if (eqz(c)) goto B_d;
  d = i32_wrap_i64(c);
  loop L_e {
    b = b + -1;
    b[0] = d - (e = d / 10) * 10 | 48;
    var f:int = d > 9;
    d = e;
    if (f) continue L_e;
  }
  label B_d:
  return b;
}

function f_ca(a:int, b:int, c:int, d:int, e:int) {
  var f:int = g_a - 256;
  g_a = f;
  if (c <= d) goto B_a;
  if (e & 73728) goto B_a;
  f_g(f, b, select_if(d = c - d, 256, c = d < 256));
  if (c) goto B_b;
  loop L_c {
    f_w(a, f, 256);
    d = d + -256;
    if (d > 255) continue L_c;
  }
  label B_b:
  f_w(a, f, d);
  label B_a:
  g_a = f + 256;
}

function f_da(a:int, b:int, c:int):int {
  return f_u(a, b, c, 1, 2)
}

function f_ea(a:int, b:double, c:int, d:int, e:int, f:int):int {
  var i:int;
  var y:long;
  var k:int;
  var j:ubyte_ptr;
  var l:int;
  var o:int;
  var q:int;
  var s:int;
  var aa:long;
  var p:int;
  var m:int;
  var w:byte_ptr;
  var v:int_ptr;
  var x:ubyte_ptr;
  var t:byte_ptr;
  var u:int;
  var ba:double;
  var g:int_ptr = g_a - 560;
  g_a = g;
  var h:int = 0;
  g[11] = 0;
  y = f_ga(b);
  if (y > -1L) goto B_b;
  i = 1;
  j = 65546;
  b = -(b);
  y = f_ga(b);
  goto B_a;
  label B_b:
  if (eqz(e & 2048)) goto B_c;
  i = 1;
  j = 65549;
  goto B_a;
  label B_c:
  j = select_if(65552, 65547, i = e & 1);
  h = eqz(i);
  label B_a:
  if ((y & 9218868437227405312L) != 9218868437227405312L) goto B_e;
  f_ca(a, 32, c, k = i + 3, e & -65537);
  f_w(a, j, i);
  f_w(a,
      select_if(select_if(65565, 65573, l = f & 32),
                select_if(65569, 65577, l),
                b != b),
      3);
  f_ca(a, 32, c, k, e ^ 8192);
  m = select_if(k, c, k > c);
  goto B_d;
  label B_e:
  var n:int = g + 16;
  b = f_r(b, g + 44);
  b = b + b;
  if (b == 0.0) goto B_i;
  g[11] = (k = g[11]) + -1;
  o = f | 32;
  if (o != 97) goto B_h;
  goto B_f;
  label B_i:
  o = f | 32;
  if (o == 97) goto B_f;
  p = select_if(6, d, d < 0);
  q = g[11];
  goto B_g;
  label B_h:
  g[11] = (q = k + -29);
  p = select_if(6, d, d < 0);
  b = b * 268435456.0;
  label B_g:
  var r:int = g + 48 + select_if(0, 288, q < 0);
  l = r;
  loop L_j {
    if (eqz(b < 4294967296.0 & b >= 0.0)) goto B_l;
    k = i32_trunc_f64_u(b);
    goto B_k;
    label B_l:
    k = 0;
    label B_k:
    l[0]:int = k;
    l = l + 4;
    b = (b - f64_convert_i32_u(k)) * 1000000000.0;
    if (b != 0.0) continue L_j;
  }
  if (q >= 1) goto B_n;
  d = q;
  k = l;
  s = r;
  goto B_m;
  label B_n:
  s = r;
  d = q;
  loop L_o {
    d = select_if(d, 29, d < 29);
    k = l + -4;
    if (k < s) goto B_p;
    var z:long = i64_extend_i32_u(d);
    y = 0L;
    loop L_q {
      k[0]:int =
        (aa = (k[0]:uint << z) + (y & 4294967295L)) -
        (y = aa / 1000000000L) * 1000000000L;
      k = k + -4;
      if (k >= s) continue L_q;
    }
    if (aa < 1000000000L) goto B_p;
    s = s + -4;
    s[0]:int = y;
    label B_p:
    loop L_s {
      k = l;
      if (k <= s) goto B_r;
      l = k + -4;
      if (eqz(l[0]:int)) continue L_s;
    }
    label B_r:
    g[11] = (d = g[11] - d);
    l = k;
    if (d > 0) continue L_o;
  }
  label B_m:
  if (d > -1) goto B_t;
  t = (p + 25) / 9 + 1;
  u = o == 102;
  loop L_u {
    l = 0 - d;
    v = select_if(l, 9, l < 9);
    if (s < k) goto B_w;
    l = eqz(s[0]:int) << 2;
    goto B_v;
    label B_w:
    w = 1000000000 >> v;
    x = -1 << v ^ -1;
    d = 0;
    l = s;
    loop L_x {
      l[0]:int = ((m = l[0]:int) >> v) + d;
      d = (m & x) * w;
      l = l + 4;
      if (l < k) continue L_x;
    }
    l = eqz(s[0]:int) << 2;
    if (eqz(d)) goto B_v;
    k[0]:int = d;
    k = k + 4;
    label B_v:
    g[11] = (d = g[11] + v);
    l = select_if(r, s = s + l, u);
    k = select_if(l + (t << 2), k, k - l >> 2 > t);
    if (d < 0) continue L_u;
  }
  label B_t:
  d = 0;
  if (s >= k) goto B_y;
  d = (r - s >> 2) * 9;
  l = 10;
  m = s[0]:int;
  if (m < 10) goto B_y;
  loop L_z {
    d = d + 1;
    if (m >= (l = l * 10)) continue L_z;
  }
  label B_y:
  l = p - select_if(0, d, o == 102) - (p != 0 & o == 103);
  if (l >= (k - r >> 2) * 9 + -9) goto B_aa;
  v = g + 48 + select_if(-4092, -3804, q < 0) +
      ((w = (m = l + 9216) / 9) << 2);
  l = 10;
  m = m - w * 9;
  if (m > 7) goto B_ba;
  loop L_ca {
    l = l * 10;
    m = m + 1;
    if (m != 8) continue L_ca;
  }
  label B_ba:
  x = v + 4;
  m = v[0];
  w = m - (t = m / l) * l;
  if (w) goto B_ea;
  if (x == k) goto B_da;
  label B_ea:
  if (t & 1) goto B_ga;
  b = 9007199254740992.0;
  if (l != 1000000000) goto B_fa;
  if (v <= s) goto B_fa;
  if (eqz((v + -4)[0]:ubyte & 1)) goto B_fa;
  label B_ga:
  b = 9007199254740994.0;
  label B_fa:
  ba = 
    select_if(
      0.5,
      select_if(select_if(1.0, 1.5, x == k), 1.5, w == (x = l >> 1)),
      w < x);
  if (h) goto B_ha;
  if (j[0] != 45) goto B_ha;
  ba = -(ba);
  b = -(b);
  label B_ha:
  v[0] = (m = m - w);
  if (b + ba == b) goto B_da;
  v[0] = (l = m + l);
  if (l < 1000000000) goto B_ia;
  loop L_ja {
    v[0] = 0;
    v = v + -4;
    if (v >= s) goto B_ka;
    s = s + -4;
    s[0]:int = 0;
    label B_ka:
    v[0] = (l = v[0] + 1);
    if (l > 999999999) continue L_ja;
  }
  label B_ia:
  d = (r - s >> 2) * 9;
  l = 10;
  m = s[0]:int;
  if (m < 10) goto B_da;
  loop L_la {
    d = d + 1;
    if (m >= (l = l * 10)) continue L_la;
  }
  label B_da:
  l = v + 4;
  k = select_if(l, k, k > l);
  label B_aa:
  loop L_na {
    l = k;
    m = l <= s;
    if (m) goto B_ma;
    k = l + -4;
    if (eqz(k[0]:int)) continue L_na;
  }
  label B_ma:
  if (o == 103) goto B_pa;
  v = e & 8;
  goto B_oa;
  label B_pa:
  p = 
    select_if(d ^ -1, -1, v = (k = select_if(p, 1, p)) > d & d > -5) + k;
  f = select_if(-1, -2, v) + f;
  v = e & 8;
  if (v) goto B_oa;
  k = -9;
  if (m) goto B_qa;
  v = (l + -4)[0]:int;
  if (eqz(v)) goto B_qa;
  m = 10;
  k = 0;
  if (v % 10) goto B_qa;
  loop L_ra {
    w = k;
    k = w + 1;
    if (eqz(v % (m = m * 10))) continue L_ra;
  }
  k = w ^ -1;
  label B_qa:
  m = (l - r >> 2) * 9;
  if ((f & -33) != 70) goto B_sa;
  v = 0;
  p = select_if(p, k = select_if(k = m + k + -9, 0, k > 0), p < k);
  goto B_oa;
  label B_sa:
  v = 0;
  p = select_if(p, k = select_if(k = d + m + k + -9, 0, k > 0), p < k);
  label B_oa:
  m = -1;
  if (p > select_if(2147483645, 2147483646, w = p | v)) goto B_d;
  x = p + (w != 0) + 1;
  u = f & -33;
  if (u != 70) goto B_ua;
  if (d > (x ^ 2147483647)) goto B_d;
  k = select_if(d, 0, d > 0);
  goto B_ta;
  label B_ua:
  if (
    n - (k = f_ba(i64_extend_i32_u((d ^ (k = d >> 31)) - k), n)) > 1) goto B_va;
  loop L_wa {
    k = k + -1;
    k[0]:byte = 48;
    if (n - k < 2) continue L_wa;
  }
  label B_va:
  t = k + -2;
  t[0] = f;
  m = -1;
  (k + -1)[0]:byte = select_if(45, 43, d < 0);
  k = n - t;
  if (k > (x ^ 2147483647)) goto B_d;
  label B_ta:
  m = -1;
  k = k + x;
  if (k > (i ^ 2147483647)) goto B_d;
  f_ca(a, 32, c, x = k + i, e);
  f_w(a, j, i);
  f_ca(a, 48, c, x, e ^ 65536);
  if (u != 70) goto B_ab;
  d = g + 16 | 9;
  m = select_if(r, s, s > r);
  s = m;
  loop L_bb {
    k = f_ba(s[0]:uint, d);
    if (s == m) goto B_db;
    if (k <= g + 16) goto B_cb;
    loop L_eb {
      k = k + -1;
      k[0]:byte = 48;
      if (k > g + 16) continue L_eb;
      goto B_cb;
    }
    unreachable;
    label B_db:
    if (k != d) goto B_cb;
    k = k + -1;
    k[0]:byte = 48;
    label B_cb:
    f_w(a, k, d - k);
    s = s + 4;
    if (s <= r) continue L_bb;
  }
  if (eqz(w)) goto B_fb;
  f_w(a, 65757, 1);
  label B_fb:
  if (s >= l) goto B_za;
  if (p < 1) goto B_za;
  loop L_gb {
    k = f_ba(s[0]:uint, d);
    if (k <= g + 16) goto B_hb;
    loop L_ib {
      k = k + -1;
      k[0]:byte = 48;
      if (k > g + 16) continue L_ib;
    }
    label B_hb:
    f_w(a, k, select_if(p, 9, p < 9));
    k = p + -9;
    s = s + 4;
    if (s >= l) goto B_ya;
    m = p > 9;
    p = k;
    if (m) continue L_gb;
    goto B_ya;
  }
  unreachable;
  label B_ab:
  if (p < 0) goto B_jb;
  w = select_if(l, s + 4, l > s);
  d = g + 16 | 9;
  l = s;
  loop L_kb {
    k = f_ba(l[0]:uint, d);
    if (k != d) goto B_lb;
    k = k + -1;
    k[0]:byte = 48;
    label B_lb:
    if (l == s) goto B_nb;
    if (k <= g + 16) goto B_mb;
    loop L_ob {
      k = k + -1;
      k[0]:byte = 48;
      if (k > g + 16) continue L_ob;
      goto B_mb;
    }
    unreachable;
    label B_nb:
    f_w(a, k, 1);
    k = k + 1;
    if (eqz(p | v)) goto B_mb;
    f_w(a, 65757, 1);
    label B_mb:
    f_w(a, k, select_if(m = d - k, p, p > m));
    p = p - m;
    l = l + 4;
    if (l >= w) goto B_jb;
    if (p > -1) continue L_kb;
  }
  label B_jb:
  f_ca(a, 48, p + 18, 18, 0);
  f_w(a, t, n - t);
  goto B_xa;
  label B_za:
  k = p;
  label B_ya:
  f_ca(a, 48, k + 9, 9, 0);
  label B_xa:
  f_ca(a, 32, c, x, e ^ 8192);
  m = select_if(x, c, x > c);
  goto B_d;
  label B_f:
  x = j + ((f << 26) >> 31 & 9);
  if (d > 11) goto B_pb;
  k = 12 - d;
  ba = 16.0;
  loop L_qb {
    ba = ba * 16.0;
    k = k + -1;
    if (k) continue L_qb;
  }
  if (x[0] != 45) goto B_rb;
  b = -(ba + -(b) - ba);
  goto B_pb;
  label B_rb:
  b = b + ba - ba;
  label B_pb:
  k = g[11];
  k = f_ba(i64_extend_i32_u((k ^ (k = k >> 31)) - k), n);
  if (k != n) goto B_sb;
  k = k + -1;
  k[0]:byte = 48;
  label B_sb:
  v = i | 2;
  s = f & 32;
  l = g[11];
  w = k + -2;
  w[0] = f + 15;
  (k + -1)[0]:byte = select_if(45, 43, l < 0);
  m = e & 8;
  l = g + 16;
  loop L_tb {
    k = l;
    if (eqz(abs(b) < 2147483648.0)) goto B_vb;
    l = i32_trunc_f64_s(b);
    goto B_ub;
    label B_vb:
    l = -2147483648;
    label B_ub:
    k[0]:byte = (l + 66288)[0]:ubyte | s;
    b = (b - f64_convert_i32_s(l)) * 16.0;
    l = k + 1;
    if (l - g + 16 != 1) goto B_wb;
    if (m) goto B_xb;
    if (d > 0) goto B_xb;
    if (b == 0.0) goto B_wb;
    label B_xb:
    k[1]:byte = 46;
    l = k + 2;
    label B_wb:
    if (b != 0.0) continue L_tb;
  }
  m = -1;
  if (2147483645 - (t = v + (s = n - w)) < d) goto B_d;
  f_ca(
    a,
    32,
    c,
    l = 
      t +
      (d = select_if(select_if(d + 2, k = l - g + 16, k + -2 < d), k, d)),
    e);
  f_w(a, x, v);
  f_ca(a, 48, c, l, e ^ 65536);
  f_w(a, g + 16, k);
  f_ca(a, 48, d - k, 0, 0);
  f_w(a, w, s);
  f_ca(a, 32, c, l, e ^ 8192);
  m = select_if(l, c, l > c);
  label B_d:
  g_a = g + 560;
  return m;
}

function f_fa(a:double_ptr, b:int_ptr) {
  var c:long_ptr;
  b[0] = (c = b[0] + 7 & -8) + 16;
  a[0] = f_sa(c[0], (c + 8)[0]:long);
}

function f_ga(a:double):long {
  return i64_reinterpret_f64(a)
}

function f_ha(a:byte_ptr, b:int, c:int, d:int):int {
  var f:int;
  var e:int_ptr = g_a - 160;
  g_a = e;
  e[37] = (a = select_if(a, e + 158, b));
  e[38] = select_if(0, f = b + -1, f > b);
  e = f_g(e, 0, 144);
  e[19] = -1;
  e[9] = 3;
  e[20] = -1;
  e[11] = e + 159;
  e[21] = e + 148;
  a[0] = 0;
  b = f_da(e, c, d);
  g_a = e + 160;
  return b;
}

function f_ia(a:int_ptr, b:int, c:int):int {
  var g:int;
  var h:int;
  var f:int;
  var d:{ a:int, b:int } = a[21];
  var e:byte_ptr = d.a;
  f = d.b;
  h = select_if(f, h = a[5] - (g = a[7]), f < h);
  if (eqz(h)) goto B_a;
  f_s(e, g, h);
  d.a = (e = d.a + h);
  d.b = (f = d.b - h);
  label B_a:
  f = select_if(f, c, f < c);
  if (eqz(f)) goto B_b;
  f_s(e, b, f);
  d.a = (e = d.a + f);
  d.b = d.b - f;
  label B_b:
  e[0] = 0;
  a[7] = (d = a[11]);
  a[5] = d;
  return c;
}

function f_ja(a:int, b:int, c:int):int {
  return f_ha(a, 2147483647, b, c)
}

function f_ka():int {
  return 42
}

function f_la():int {
  return f_ka()
}

function f_ma():int {
  return 66416
}

function f_na() {
  0[16628]:int = 66392;
  0[16610]:int = f_la();
}

function f_oa(a:{ a:byte, b:byte, c:byte, d:byte }, b:int, c:int):int {
  var d:int = 1;
  if (eqz(a)) goto B_b;
  if (b <= 127) goto B_a;
  if ((f_ma()[24]:int)[0]:int) goto B_d;
  if ((b & -128) == 57216) goto B_a;
  f_q()[0]:int = 25;
  goto B_c;
  label B_d:
  if (b > 2047) goto B_e;
  a.b = (b & 63) | 128;
  a.a = b >> 6 | 192;
  return 2;
  label B_e:
  if (b < 55296) goto B_g;
  if ((b & -8192) != 57344) goto B_f;
  label B_g:
  a.c = (b & 63) | 128;
  a.a = b >> 12 | 224;
  a.b = (b >> 6 & 63) | 128;
  return 3;
  label B_f:
  if (b + -65536 > 1048575) goto B_h;
  a.d = (b & 63) | 128;
  a.a = b >> 18 | 240;
  a.c = (b >> 6 & 63) | 128;
  a.b = (b >> 12 & 63) | 128;
  return 4;
  label B_h:
  f_q()[0]:int = 25;
  label B_c:
  d = -1;
  label B_b:
  return d;
  label B_a:
  a.a = b;
  return 1;
}

function f_pa(a:int, b:int):int {
  if (a) goto B_a;
  return 0;
  label B_a:
  return f_oa(a, b, 0);
}

function f_qa(a:{ a:long, b:long }, b:long, c:long, d:int) {
  var e:long;
  if (eqz(d & 64)) goto B_b;
  c = b << i64_extend_i32_u(d + -64);
  b = 0L;
  goto B_a;
  label B_b:
  if (eqz(d)) goto B_a;
  c = b >> i64_extend_i32_u(64 - d) | c << (e = i64_extend_i32_u(d));
  b = b << e;
  label B_a:
  a.a = b;
  a.b = c;
}

function f_ra(a:{ a:long, b:long }, b:long, c:long, d:int) {
  var e:long;
  if (eqz(d & 64)) goto B_b;
  b = c >> i64_extend_i32_u(d + -64);
  c = 0L;
  goto B_a;
  label B_b:
  if (eqz(d)) goto B_a;
  b = c << i64_extend_i32_u(64 - d) | b >> (e = i64_extend_i32_u(d));
  c = c >> e;
  label B_a:
  a.a = b;
  a.b = c;
}

function f_sa(a:long, b:long):double {
  var i:long;
  var d:int;
  var e:int;
  var g:int;
  var f:int;
  var c:long_ptr = g_a - 32;
  g_a = c;
  var h:long = b & 281474976710655L;
  i = b >> 48L & 32767L;
  d = i32_wrap_i64(i);
  if (d + -15361 > 2045) goto B_b;
  h = a >> 60L | h << 4L;
  i = i64_extend_i32_u(d + -15360);
  a = a & 1152921504606846975L;
  if (a < 576460752303423489L) goto B_d;
  h = h + 1L;
  goto B_c;
  label B_d:
  if (a != 576460752303423488L) goto B_c;
  h = (h & 1L) + h;
  label B_c:
  a = select_if(0L, h, d = h > 4503599627370495L);
  h = i64_extend_i32_u(d) + i;
  goto B_a;
  label B_b:
  if (eqz(a | h)) goto B_e;
  if (i != 32767L) goto B_e;
  a = (a >> 60L | h << 4L) | 2251799813685248L;
  h = 2047L;
  goto B_a;
  label B_e:
  if (d <= 17406) goto B_f;
  h = 2047L;
  a = 0L;
  goto B_a;
  label B_f:
  f = select_if(15360, 15361, e = eqz(i));
  g = f - d;
  if (g <= 112) goto B_g;
  a = 0L;
  h = 0L;
  goto B_a;
  label B_g:
  f_qa(c + 16, a, h = select_if(h, h | 281474976710656L, e), 128 - g);
  f_ra(c, a, h, g);
  h = c[0];
  a = h >> 60L | (c + 8)[0]:long << 4L;
  h = (h & 1152921504606846975L) |
      i64_extend_i32_u(f != d & (c[2] | (c + 16 + 8)[0]:long) != 0L);
  if (h < 576460752303423489L) goto B_i;
  a = a + 1L;
  goto B_h;
  label B_i:
  if (h != 576460752303423488L) goto B_h;
  a = (a & 1L) + a;
  label B_h:
  a = select_if(a ^ 4503599627370496L, a, d = a > 4503599627370495L);
  h = i64_extend_i32_u(d);
  label B_a:
  g_a = c + 32;
  return 
    f64_reinterpret_i64((h << 52L | (b & -9223372036854775808L)) | a);
}

function f_ta(a:int) {
  g_b = a
}

function f_ua():int {
  return g_b
}

export function fflush(a:int):int {
  var c:int;
  var b:int;
  var d:int;
  if (a) goto B_a;
  b = 0;
  if (eqz(0[16588]:int)) goto B_b;
  b = fflush(0[16588]:int);
  label B_b:
  if (eqz(0[16588]:int)) goto B_c;
  b = fflush(0[16588]:int) | b;
  label B_c:
  a = f_l()[0]:int;
  if (eqz(a)) goto B_d;
  loop L_e {
    c = 0;
    if (a[19]:int < 0) goto B_f;
    c = f_h(a);
    label B_f:
    if (a[5]:int == a[7]:int) goto B_g;
    b = fflush(a) | b;
    label B_g:
    if (eqz(c)) goto B_h;
    f_i(a);
    label B_h:
    a = a[14]:int;
    if (a) continue L_e;
  }
  label B_d:
  f_m();
  return b;
  label B_a:
  if (a[19]:int >= 0) goto B_j;
  c = 1;
  goto B_i;
  label B_j:
  c = eqz(f_h(a));
  label B_i:
  if (a[5]:int == a[7]:int) goto B_m;
  call_indirect(a, 0, 0, a[9]:int);
  if (a[5]:int) goto B_m;
  b = -1;
  if (eqz(c)) goto B_l;
  goto B_k;
  label B_m:
  b = a[1]:int;
  if (b == (d = a[2]:int)) goto B_n;
  call_indirect(a, i64_extend_i32_s(b - d), 1, a[10]:int);
  label B_n:
  b = 0;
  a[7]:int = 0;
  a[2]:long = 0L;
  a[1]:long@4 = 0L;
  if (c) goto B_k;
  label B_l:
  f_i(a);
  label B_k:
  return b;
}

export function emscripten_stack_init() {
  g_d = 65536;
  g_c = 0 + 15 & -16;
}

export function emscripten_stack_get_free():int {
  return g_a - g_c
}

export function emscripten_stack_get_base():int {
  return g_d
}

export function emscripten_stack_get_end():int {
  return g_c
}

export function emscripten_stack_restore(a:int) {
  g_a = a
}

export function emscripten_stack_alloc(a:int):int {
  var b:int = g_a - a & -16;
  g_a = b;
  return b;
}

export function emscripten_stack_get_current():int {
  return g_a
}

function f_db():int {
  return memory_size() << 16
}

function f_eb(a:int):int {
  var c:int;
  var b:int = d_b[6]:int;
  a = b + (c = a + 7 & -8);
  if (eqz(c)) goto B_c;
  if (a <= b) goto B_b;
  label B_c:
  if (a <= f_db()) goto B_a;
  if (env_emscripten_resize_heap(a)) goto B_a;
  label B_b:
  f_q()[0]:int = 48;
  return -1;
  label B_a:
  d_b[6]:int = a;
  return b;
}

export function malloc(a:int):int {
  var d:{ a:int, b:int, c:int }
  var e:int;
  var f:{ a:int, b:int, c:int, d:int, e:int, f:int, g:int }
  var g:int;
  var h:int;
  var c:int_ptr;
  var i:int;
  var l:int_ptr;
  var k:int_ptr;
  var j:int;
  var b:int = g_a - 16;
  g_a = b;
  if (a > 244) goto B_k;
  c = 0[16637]:int;
  a = c >> (e = (d = select_if(16, a + 11 & 504, a < 11)) >> 3);
  if (eqz(a & 3)) goto B_l;
  d = ((a ^ -1) & 1) + e;
  e = d << 3;
  a = e + 66588;
  if (a != (f = (e = (e + 66596)[0]:int)[2]:int)) goto B_n;
  0[16637]:int = c & -2 << d;
  goto B_m;
  label B_n:
  f.d = a;
  a[2]:int = f;
  label B_m:
  a = e + 8;
  e[1]:int = (d = d << 3) | 3;
  e = e + d;
  e[1]:int = e[1]:int | 1;
  goto B_a;
  label B_l:
  if (d <= (g = 0[16639]:int)) goto B_j;
  if (eqz(a)) goto B_o;
  e = ctz(a << e & ((a = 2 << e) | 0 - a));
  a = e << 3;
  f = a + 66588;
  if (f != (h = (a = (a + 66596)[0]:int)[2]:int)) goto B_q;
  0[16637]:int = (c = c & -2 << e);
  goto B_p;
  label B_q:
  h[3]:int = f;
  f.c = h;
  label B_p:
  a[1]:int = d | 3;
  h = a + d;
  h[1]:int = (d = (e = e << 3) - d) | 1;
  (a + e)[0]:int = d;
  if (eqz(g)) goto B_r;
  f = (g & -8) + 66588;
  e = 0[16642]:int;
  if (c & (i = 1 << (g >> 3))) goto B_t;
  0[16637]:int = c | i;
  i = f;
  goto B_s;
  label B_t:
  i = f.c;
  label B_s:
  f.c = e;
  i[3]:int = e;
  e[3]:int = f;
  e[2]:int = i;
  label B_r:
  a = a + 8;
  0[16642]:int = h;
  0[16639]:int = d;
  goto B_a;
  label B_o:
  j = 0[16638]:int;
  if (eqz(j)) goto B_j;
  h = ((ctz(j) << 2) + 66852)[0]:int;
  e = (h[1]:int & -8) - d;
  f = h;
  loop L_v {
    a = f.e;
    if (a) goto B_w;
    a = f.f;
    if (eqz(a)) goto B_u;
    label B_w:
    f = (a[1]:int & -8) - d;
    e = select_if(f, e, f = f < e);
    h = select_if(a, h, f);
    f = a;
    continue L_v;
  }
  unreachable;
  label B_u:
  k = h[6]:int;
  a = h[3]:int;
  if (a == h) goto B_x;
  f = h[2]:int;
  f.d = a;
  a[2]:int = f;
  goto B_b;
  label B_x:
  f = h[5]:int;
  if (eqz(f)) goto B_z;
  i = h + 20;
  goto B_y;
  label B_z:
  f = h[4]:int;
  if (eqz(f)) goto B_i;
  i = h + 16;
  label B_y:
  loop L_aa {
    l = i;
    a = f;
    i = a + 20;
    f = a[5]:int;
    if (f) continue L_aa;
    i = a + 16;
    f = a[4]:int;
    if (f) continue L_aa;
  }
  l[0] = 0;
  goto B_b;
  label B_k:
  d = -1;
  if (a > -65) goto B_j;
  e = a + 11;
  d = e & -8;
  k = 0[16638]:int;
  if (eqz(k)) goto B_j;
  g = 31;
  if (a > 16777204) goto B_ba;
  g = (d >> 38 - (a = clz(e >> 8)) & 1) - (a << 1) + 62;
  label B_ba:
  e = 0 - d;
  f = ((g << 2) + 66852)[0]:int;
  if (f) goto B_fa;
  a = 0;
  i = 0;
  goto B_ea;
  label B_fa:
  a = 0;
  h = d << select_if(0, 25 - (g >> 1), g == 31);
  i = 0;
  loop L_ga {
    c = (f.b & -8) - d;
    if (c >= e) goto B_ha;
    e = c;
    i = f;
    if (c) goto B_ha;
    e = 0;
    i = f;
    a = f;
    goto B_da;
    label B_ha:
    a = 
      select_if(
        select_if(a, c = f.f, c == (l = (f + (h >> 29 & 4) + 16)[0]:int)),
        a,
        c);
    h = h << 1;
    f = l;
    if (l) continue L_ga;
  }
  label B_ea:
  if (a | i) goto B_ia;
  i = 0;
  a = 2 << g;
  a = (a | 0 - a) & k;
  if (eqz(a)) goto B_j;
  a = ((ctz(a) << 2) + 66852)[0]:int;
  label B_ia:
  if (eqz(a)) goto B_ca;
  label B_da:
  loop L_ja {
    c = (a[1]:int & -8) - d;
    h = c < e;
    f = a[4]:int;
    if (f) goto B_ka;
    f = a[5]:int;
    label B_ka:
    e = select_if(c, e, h);
    i = select_if(a, i, h);
    a = f;
    if (f) continue L_ja;
  }
  label B_ca:
  if (eqz(i)) goto B_j;
  if (e >= 0[16639]:int - d) goto B_j;
  l = i[6]:int;
  a = i[3]:int;
  if (a == i) goto B_la;
  f = i[2]:int;
  f.d = a;
  a[2]:int = f;
  goto B_c;
  label B_la:
  f = i[5]:int;
  if (eqz(f)) goto B_na;
  h = i + 20;
  goto B_ma;
  label B_na:
  f = i[4]:int;
  if (eqz(f)) goto B_h;
  h = i + 16;
  label B_ma:
  loop L_oa {
    c = h;
    a = f;
    h = a + 20;
    f = a[5]:int;
    if (f) continue L_oa;
    h = a + 16;
    f = a[4]:int;
    if (f) continue L_oa;
  }
  c[0] = 0;
  goto B_c;
  label B_j:
  a = 0[16639]:int;
  if (a < d) goto B_pa;
  e = 0[16642]:int;
  f = a - d;
  if (f < 16) goto B_ra;
  h = e + d;
  h[1]:int = f | 1;
  (e + a)[0]:int = f;
  e[1]:int = d | 3;
  goto B_qa;
  label B_ra:
  e[1]:int = a | 3;
  a = e + a;
  a[1]:int = a[1]:int | 1;
  h = 0;
  f = 0;
  label B_qa:
  0[16639]:int = f;
  0[16642]:int = h;
  a = e + 8;
  goto B_a;
  label B_pa:
  h = 0[16640]:int;
  if (h <= d) goto B_sa;
  0[16640]:int = (e = h - d);
  0[16643]:int = (f = (a = 0[16643]:int) + d);
  f.b = e | 1;
  a[1]:int = d | 3;
  a = a + 8;
  goto B_a;
  label B_sa:
  if (eqz(0[16755]:int)) goto B_ua;
  e = 0[16757]:int;
  goto B_ta;
  label B_ua:
  0[16758]:long@4 = -1L;
  0[16756]:long@4 = 17592186048512L;
  0[16755]:int = (b + 12 & -16) ^ 1431655768;
  0[16760]:int = 0;
  0[16748]:int = 0;
  e = 4096;
  label B_ta:
  a = 0;
  c = e + (g = d + 47);
  i = c & (l = 0 - e);
  if (i <= d) goto B_a;
  a = 0;
  e = 0[16747]:int;
  if (eqz(e)) goto B_va;
  f = 0[16745]:int;
  k = f + i;
  if (k <= f) goto B_a;
  if (k > e) goto B_a;
  label B_va:
  if (0[66992]:ubyte & 4) goto B_xa;
  e = 0[16643]:int;
  if (eqz(e)) goto B_cb;
  a = 66996;
  loop L_db {
    f = a[0]:int;
    if (f > e) goto B_eb;
    if (f + a[1]:int > e) goto B_bb;
    label B_eb:
    a = a[2]:int;
    if (a) continue L_db;
  }
  label B_cb:
  h = f_eb(0);
  if (h == -1) goto B_ya;
  c = i;
  a = 0[16756]:int;
  e = a + -1;
  if (eqz(e & h)) goto B_fb;
  c = i - h + (e + h & 0 - a);
  label B_fb:
  if (c <= d) goto B_ya;
  a = 0[16747]:int;
  if (eqz(a)) goto B_gb;
  e = 0[16745]:int;
  f = e + c;
  if (f <= e) goto B_ya;
  if (f > a) goto B_ya;
  label B_gb:
  a = f_eb(c);
  if (a != h) goto B_ab;
  goto B_wa;
  label B_bb:
  c = c - h & l;
  h = f_eb(c);
  if (h == a[0]:int + a[1]:int) goto B_za;
  a = h;
  label B_ab:
  if (a == -1) goto B_ya;
  if (c < d + 48) goto B_hb;
  h = a;
  goto B_wa;
  label B_hb:
  e = g - c + (e = 0[16757]:int) & 0 - e;
  if (f_eb(e) == -1) goto B_ya;
  c = e + c;
  h = a;
  goto B_wa;
  label B_za:
  if (h != -1) goto B_wa;
  label B_ya:
  0[16748]:int = 0[16748]:int | 4;
  label B_xa:
  h = f_eb(i);
  a = f_eb(0);
  if (h == -1) goto B_e;
  if (a == -1) goto B_e;
  if (h >= a) goto B_e;
  c = a - h;
  if (c <= d + 40) goto B_e;
  label B_wa:
  0[16745]:int = (a = 0[16745]:int + c);
  if (a <= 0[16746]:int) goto B_ib;
  0[16746]:int = a;
  label B_ib:
  e = 0[16643]:int;
  if (eqz(e)) goto B_kb;
  a = 66996;
  loop L_lb {
    if (h == (f = a[0]:int) + (i = a[1]:int)) goto B_jb;
    a = a[2]:int;
    if (a) continue L_lb;
    goto B_g;
  }
  unreachable;
  label B_kb:
  a = 0[16641]:int;
  if (eqz(a)) goto B_nb;
  if (h >= a) goto B_mb;
  label B_nb:
  0[16641]:int = h;
  label B_mb:
  a = 0;
  0[16750]:int = c;
  0[16749]:int = h;
  0[16645]:int = -1;
  0[16646]:int = 0[16755]:int;
  0[16752]:int = 0;
  loop L_ob {
    e = a << 3;
    (e + 66596)[0]:int = (f = e + 66588);
    (e + 66600)[0]:int = f;
    a = a + 1;
    if (a != 32) continue L_ob;
  }
  0[16640]:int = (f = (a = c + -40) - (e = -8 - h & 7));
  0[16643]:int = (e = h + e);
  e[1]:int = f | 1;
  (h + a)[1]:int = 40;
  0[16644]:int = 0[16759]:int;
  goto B_f;
  label B_jb:
  if (e >= h) goto B_g;
  if (e < f) goto B_g;
  if (a[3]:int & 8) goto B_g;
  a[1]:int = i + c;
  0[16643]:int = (f = e + (a = -8 - e & 7));
  0[16640]:int = (a = (h = 0[16640]:int + c) - a);
  f.b = a | 1;
  (e + h)[1]:int = 40;
  0[16644]:int = 0[16759]:int;
  goto B_f;
  label B_i:
  a = 0;
  goto B_b;
  label B_h:
  a = 0;
  goto B_c;
  label B_g:
  if (h >= 0[16641]:int) goto B_pb;
  0[16641]:int = h;
  label B_pb:
  f = h + c;
  a = 66996;
  loop L_sb {
    i = a[0]:int;
    if (i == f) goto B_rb;
    a = a[2]:int;
    if (a) continue L_sb;
    goto B_qb;
  }
  unreachable;
  label B_rb:
  if (eqz(a[12]:ubyte & 8)) goto B_d;
  label B_qb:
  a = 66996;
  loop L_ub {
    f = a[0]:int;
    if (f > e) goto B_vb;
    f = f + a[1]:int;
    if (f > e) goto B_tb;
    label B_vb:
    a = a[2]:int;
    continue L_ub;
  }
  unreachable;
  label B_tb:
  0[16640]:int = (l = (a = c + -40) - (i = -8 - h & 7));
  0[16643]:int = (i = h + i);
  i[1]:int = l | 1;
  (h + a)[1]:int = 40;
  0[16644]:int = 0[16759]:int;
  i = select_if(e, a = f + (39 - f & 7) + -47, a < e + 16);
  i[1]:int = 27;
  (i + 16)[0]:long@4 = 0[16751]:long@4;
  i[2]:long@4 = 0[16749]:long@4;
  0[16751]:int = i + 8;
  0[16750]:int = c;
  0[16749]:int = h;
  0[16752]:int = 0;
  a = i + 24;
  loop L_wb {
    a[1]:int = 7;
    h = a + 8;
    a = a + 4;
    if (h < f) continue L_wb;
  }
  if (i == e) goto B_f;
  i[1]:int = i[1]:int & -2;
  e[1]:int = (h = i - e) | 1;
  i[0]:int = h;
  if (h > 255) goto B_yb;
  a = (h & -8) + 66588;
  f = 0[16637]:int;
  if (f & (h = 1 << (h >> 3))) goto B_ac;
  0[16637]:int = f | h;
  f = a;
  goto B_zb;
  label B_ac:
  f = a[2]:int;
  label B_zb:
  a[2]:int = e;
  f.d = e;
  h = 12;
  i = 8;
  goto B_xb;
  label B_yb:
  a = 31;
  if (h > 16777215) goto B_bc;
  a = (h >> 38 - (a = clz(h >> 8)) & 1) - (a << 1) + 62;
  label B_bc:
  e[7]:int = a;
  e[4]:long@4 = 0L;
  f = (a << 2) + 66852;
  i = 0[16638]:int;
  if (i & (c = 1 << a)) goto B_ec;
  0[16638]:int = i | c;
  f.a = e;
  e[6]:int = f;
  goto B_dc;
  label B_ec:
  a = h << select_if(0, 25 - (a >> 1), a == 31);
  i = f.a;
  loop L_fc {
    f = i;
    if ((f.b & -8) == h) goto B_cc;
    i = a >> 29;
    a = a << 1;
    c = f + (i & 4) + 16;
    i = c[0];
    if (i) continue L_fc;
  }
  c[0] = e;
  e[6]:int = f;
  label B_dc:
  h = 8;
  i = 12;
  f = e;
  a = e;
  goto B_xb;
  label B_cc:
  a = f.c;
  a[3]:int = e;
  f.c = e;
  e[2]:int = a;
  a = 0;
  h = 24;
  i = 12;
  label B_xb:
  (e + i)[0]:int = f;
  (e + h)[0]:int = a;
  label B_f:
  a = 0[16640]:int;
  if (a <= d) goto B_e;
  0[16640]:int = (e = a - d);
  0[16643]:int = (f = (a = 0[16643]:int) + d);
  f.b = e | 1;
  a[1]:int = d | 3;
  a = a + 8;
  goto B_a;
  label B_e:
  f_q()[0]:int = 48;
  a = 0;
  goto B_a;
  label B_d:
  a[0]:int = h;
  a[1]:int = a[1]:int + c;
  a = f_gb(h, i, d);
  goto B_a;
  label B_c:
  if (eqz(l)) goto B_gc;
  if (i != (f = ((h = i[7]:int) << 2) + 66852).a) goto B_ic;
  f.a = a;
  if (a) goto B_hc;
  0[16638]:int = (k = k & -2 << h);
  goto B_gc;
  label B_ic:
  (l + select_if(16, 20, l[4] == i))[0]:int = a;
  if (eqz(a)) goto B_gc;
  label B_hc:
  a[6]:int = l;
  f = i[4]:int;
  if (eqz(f)) goto B_jc;
  a[4]:int = f;
  f.g = a;
  label B_jc:
  f = i[5]:int;
  if (eqz(f)) goto B_gc;
  a[5]:int = f;
  f.g = a;
  label B_gc:
  if (e > 15) goto B_lc;
  i[1]:int = (a = e + d) | 3;
  a = i + a;
  a[1]:int = a[1]:int | 1;
  goto B_kc;
  label B_lc:
  i[1]:int = d | 3;
  h = i + d;
  h[1]:int = e | 1;
  (h + e)[0]:int = e;
  if (e > 255) goto B_mc;
  a = (e & -8) + 66588;
  d = 0[16637]:int;
  if (d & (e = 1 << (e >> 3))) goto B_oc;
  0[16637]:int = d | e;
  e = a;
  goto B_nc;
  label B_oc:
  e = a[2]:int;
  label B_nc:
  a[2]:int = h;
  e[3]:int = h;
  h[3]:int = a;
  h[2]:int = e;
  goto B_kc;
  label B_mc:
  a = 31;
  if (e > 16777215) goto B_pc;
  a = (e >> 38 - (a = clz(e >> 8)) & 1) - (a << 1) + 62;
  label B_pc:
  h[7]:int = a;
  h[4]:long@4 = 0L;
  d = (a << 2) + 66852;
  if (k & (f = 1 << a)) goto B_sc;
  0[16638]:int = k | f;
  d.a = h;
  h[6]:int = d;
  goto B_rc;
  label B_sc:
  a = e << select_if(0, 25 - (a >> 1), a == 31);
  f = d.a;
  loop L_tc {
    d = f;
    if ((d.b & -8) == e) goto B_qc;
    f = a >> 29;
    a = a << 1;
    c = d + (f & 4) + 16;
    f = c[0];
    if (f) continue L_tc;
  }
  c[0] = h;
  h[6]:int = d;
  label B_rc:
  h[3]:int = h;
  h[2]:int = h;
  goto B_kc;
  label B_qc:
  a = d.c;
  a[3]:int = h;
  d.c = h;
  h[6]:int = 0;
  h[3]:int = d;
  h[2]:int = a;
  label B_kc:
  a = i + 8;
  goto B_a;
  label B_b:
  if (eqz(k)) goto B_uc;
  if (h != (f = ((i = h[7]:int) << 2) + 66852).a) goto B_wc;
  f.a = a;
  if (a) goto B_vc;
  0[16638]:int = j & -2 << i;
  goto B_uc;
  label B_wc:
  (k + select_if(16, 20, k[4] == h))[0]:int = a;
  if (eqz(a)) goto B_uc;
  label B_vc:
  a[6]:int = k;
  f = h[4]:int;
  if (eqz(f)) goto B_xc;
  a[4]:int = f;
  f.g = a;
  label B_xc:
  f = h[5]:int;
  if (eqz(f)) goto B_uc;
  a[5]:int = f;
  f.g = a;
  label B_uc:
  if (e > 15) goto B_zc;
  h[1]:int = (a = e + d) | 3;
  a = h + a;
  a[1]:int = a[1]:int | 1;
  goto B_yc;
  label B_zc:
  h[1]:int = d | 3;
  d = h + d;
  d.b = e | 1;
  (d + e)[0]:int = e;
  if (eqz(g)) goto B_ad;
  f = (g & -8) + 66588;
  a = 0[16642]:int;
  i = 1 << (g >> 3);
  if (i & c) goto B_cd;
  0[16637]:int = i | c;
  i = f;
  goto B_bd;
  label B_cd:
  i = f.c;
  label B_bd:
  f.c = a;
  i[3]:int = a;
  a[3]:int = f;
  a[2]:int = i;
  label B_ad:
  0[16642]:int = d;
  0[16639]:int = e;
  label B_yc:
  a = h + 8;
  label B_a:
  g_a = b + 16;
  return a;
}

function f_gb(a:int_ptr, b:int_ptr, c:int_ptr):int {
  var f:int;
  var h:int_ptr;
  var j:int_ptr;
  var d:int_ptr = a + (-8 - a & 7);
  d[1] = c | 3;
  var e:{ a:int, b:int, c:int, d:int, e:int, f:int, g:int, h:int } = 
    b + (-8 - b & 7);
  a = e - (f = d + c);
  if (e != 0[16643]:int) goto B_b;
  0[16643]:int = f;
  0[16640]:int = (c = 0[16640]:int + a);
  f[1]:int = c | 1;
  goto B_a;
  label B_b:
  if (e != 0[16642]:int) goto B_c;
  0[16642]:int = f;
  0[16639]:int = (c = 0[16639]:int + a);
  f[1]:int = c | 1;
  (f + c)[0]:int = c;
  goto B_a;
  label B_c:
  b = e.b;
  if ((b & 3) != 1) goto B_d;
  var g:int = b & -8;
  c = e.d;
  if (b > 255) goto B_f;
  if (c != (h = e.c)) goto B_g;
  0[16637]:int = 0[16637]:int & -2 << (b >> 3);
  goto B_e;
  label B_g:
  h[3] = c;
  c[2] = h;
  goto B_e;
  label B_f:
  var i:int_ptr = e.g;
  if (c == e) goto B_i;
  b = e.c;
  b[3] = c;
  c[2] = b;
  goto B_h;
  label B_i:
  b = e.f;
  if (eqz(b)) goto B_l;
  h = e + 20;
  goto B_k;
  label B_l:
  b = e.e;
  if (eqz(b)) goto B_j;
  h = e + 16;
  label B_k:
  loop L_m {
    j = h;
    c = b;
    h = c + 20;
    b = c[5];
    if (b) continue L_m;
    h = c + 16;
    b = c[4];
    if (b) continue L_m;
  }
  j[0] = 0;
  goto B_h;
  label B_j:
  c = 0;
  label B_h:
  if (eqz(i)) goto B_e;
  if (e != (b = ((h = e.h) << 2) + 66852)[0]) goto B_o;
  b[0] = c;
  if (c) goto B_n;
  0[16638]:int = 0[16638]:int & -2 << h;
  goto B_e;
  label B_o:
  (i + select_if(16, 20, i[4] == e))[0]:int = c;
  if (eqz(c)) goto B_e;
  label B_n:
  c[6] = i;
  b = e.e;
  if (eqz(b)) goto B_p;
  c[4] = b;
  b[6] = c;
  label B_p:
  b = e.f;
  if (eqz(b)) goto B_e;
  c[5] = b;
  b[6] = c;
  label B_e:
  a = g + a;
  e = e + g;
  b = e.b;
  label B_d:
  e.b = b & -2;
  f[1]:int = a | 1;
  (f + a)[0]:int = a;
  if (a > 255) goto B_q;
  c = (a & -8) + 66588;
  b = 0[16637]:int;
  if (b & (a = 1 << (a >> 3))) goto B_s;
  0[16637]:int = b | a;
  a = c;
  goto B_r;
  label B_s:
  a = c[2];
  label B_r:
  c[2] = f;
  a[3] = f;
  f[3]:int = c;
  f[2]:int = a;
  goto B_a;
  label B_q:
  c = 31;
  if (a > 16777215) goto B_t;
  c = (a >> 38 - (c = clz(a >> 8)) & 1) - (c << 1) + 62;
  label B_t:
  f[7]:int = c;
  f[4]:long@4 = 0L;
  b = (c << 2) + 66852;
  h = 0[16638]:int;
  if (h & (e = 1 << c)) goto B_w;
  0[16638]:int = h | e;
  b[0] = f;
  f[6]:int = b;
  goto B_v;
  label B_w:
  c = a << select_if(0, 25 - (c >> 1), c == 31);
  h = b[0];
  loop L_x {
    b = h;
    if ((b[1] & -8) == a) goto B_u;
    h = c >> 29;
    c = c << 1;
    e = b + (h & 4) + 16;
    h = e.a;
    if (h) continue L_x;
  }
  e.a = f;
  f[6]:int = b;
  label B_v:
  f[3]:int = f;
  f[2]:int = f;
  goto B_a;
  label B_u:
  c = b[2];
  c[3] = f;
  b[2] = f;
  f[6]:int = 0;
  f[3]:int = b;
  f[2]:int = c;
  label B_a:
  return d + 8;
}

export function free(a:int_ptr) {
  var c:int_ptr;
  var e:int_ptr;
  var f:int_ptr;
  var h:int_ptr;
  var g:int_ptr;
  if (eqz(a)) goto B_a;
  var b:int = a + -8;
  var d:{ a:int, b:int, c:int, d:int, e:int, f:int, g:int, h:int } = 
    b + (a = (c = (a + -4)[0]:int) & -8);
  if (c & 1) goto B_b;
  if (eqz(c & 2)) goto B_a;
  b = b - (e = b[0]:int);
  if (b < 0[16641]:int) goto B_a;
  a = e + a;
  if (b == 0[16642]:int) goto B_f;
  c = b[3]:int;
  if (e > 255) goto B_g;
  if (c != (f = b[2]:int)) goto B_e;
  0[16637]:int = 0[16637]:int & -2 << (e >> 3);
  goto B_b;
  label B_g:
  g = b[6]:int;
  if (c == b) goto B_h;
  e = b[2]:int;
  e[3] = c;
  c[2] = e;
  goto B_c;
  label B_h:
  e = b[5]:int;
  if (eqz(e)) goto B_j;
  f = b + 20;
  goto B_i;
  label B_j:
  e = b[4]:int;
  if (eqz(e)) goto B_d;
  f = b + 16;
  label B_i:
  loop L_k {
    h = f;
    c = e;
    f = c + 20;
    e = c[5];
    if (e) continue L_k;
    f = c + 16;
    e = c[4];
    if (e) continue L_k;
  }
  h[0] = 0;
  goto B_c;
  label B_f:
  c = d.b;
  if ((c & 3) != 3) goto B_b;
  0[16639]:int = a;
  d.b = c & -2;
  b[1]:int = a | 1;
  d.a = a;
  return ;
  label B_e:
  f[3] = c;
  c[2] = f;
  goto B_b;
  label B_d:
  c = 0;
  label B_c:
  if (eqz(g)) goto B_b;
  if (b != (e = ((f = b[7]:int) << 2) + 66852)[0]) goto B_m;
  e[0] = c;
  if (c) goto B_l;
  0[16638]:int = 0[16638]:int & -2 << f;
  goto B_b;
  label B_m:
  (g + select_if(16, 20, g[4] == b))[0]:int = c;
  if (eqz(c)) goto B_b;
  label B_l:
  c[6] = g;
  e = b[4]:int;
  if (eqz(e)) goto B_n;
  c[4] = e;
  e[6] = c;
  label B_n:
  e = b[5]:int;
  if (eqz(e)) goto B_b;
  c[5] = e;
  e[6] = c;
  label B_b:
  if (b >= d) goto B_a;
  e = d.b;
  if (eqz(e & 1)) goto B_a;
  if (e & 2) goto B_s;
  if (d != 0[16643]:int) goto B_t;
  0[16643]:int = b;
  0[16640]:int = (a = 0[16640]:int + a);
  b[1]:int = a | 1;
  if (b != 0[16642]:int) goto B_a;
  0[16639]:int = 0;
  0[16642]:int = 0;
  return ;
  label B_t:
  if (d != 0[16642]:int) goto B_u;
  0[16642]:int = b;
  0[16639]:int = (a = 0[16639]:int + a);
  b[1]:int = a | 1;
  (b + a)[0]:int = a;
  return ;
  label B_u:
  a = (e & -8) + a;
  c = d.d;
  if (e > 255) goto B_v;
  if (c != (f = d.c)) goto B_w;
  0[16637]:int = 0[16637]:int & -2 << (e >> 3);
  goto B_p;
  label B_w:
  f[3] = c;
  c[2] = f;
  goto B_p;
  label B_v:
  g = d.g;
  if (c == d) goto B_x;
  e = d.c;
  e[3] = c;
  c[2] = e;
  goto B_q;
  label B_x:
  e = d.f;
  if (eqz(e)) goto B_z;
  f = d + 20;
  goto B_y;
  label B_z:
  e = d.e;
  if (eqz(e)) goto B_r;
  f = d + 16;
  label B_y:
  loop L_aa {
    h = f;
    c = e;
    f = c + 20;
    e = c[5];
    if (e) continue L_aa;
    f = c + 16;
    e = c[4];
    if (e) continue L_aa;
  }
  h[0] = 0;
  goto B_q;
  label B_s:
  d.b = e & -2;
  b[1]:int = a | 1;
  (b + a)[0]:int = a;
  goto B_o;
  label B_r:
  c = 0;
  label B_q:
  if (eqz(g)) goto B_p;
  if (d != (e = ((f = d.h) << 2) + 66852)[0]) goto B_ca;
  e[0] = c;
  if (c) goto B_ba;
  0[16638]:int = 0[16638]:int & -2 << f;
  goto B_p;
  label B_ca:
  (g + select_if(16, 20, g[4] == d))[0]:int = c;
  if (eqz(c)) goto B_p;
  label B_ba:
  c[6] = g;
  e = d.e;
  if (eqz(e)) goto B_da;
  c[4] = e;
  e[6] = c;
  label B_da:
  e = d.f;
  if (eqz(e)) goto B_p;
  c[5] = e;
  e[6] = c;
  label B_p:
  b[1]:int = a | 1;
  (b + a)[0]:int = a;
  if (b != 0[16642]:int) goto B_o;
  0[16639]:int = a;
  return ;
  label B_o:
  if (a > 255) goto B_ea;
  c = (a & -8) + 66588;
  e = 0[16637]:int;
  if (e & (a = 1 << (a >> 3))) goto B_ga;
  0[16637]:int = e | a;
  a = c;
  goto B_fa;
  label B_ga:
  a = c[2];
  label B_fa:
  c[2] = b;
  a[3] = b;
  b[3]:int = c;
  b[2]:int = a;
  return ;
  label B_ea:
  c = 31;
  if (a > 16777215) goto B_ha;
  c = (a >> 38 - (c = clz(a >> 8)) & 1) - (c << 1) + 62;
  label B_ha:
  b[7]:int = c;
  b[4]:long@4 = 0L;
  d = (c << 2) + 66852;
  e = 0[16638]:int;
  if (e & (f = 1 << c)) goto B_la;
  0[16638]:int = e | f;
  a = 8;
  c = 24;
  f = d;
  goto B_ka;
  label B_la:
  c = a << select_if(0, 25 - (c >> 1), c == 31);
  f = d.a;
  loop L_ma {
    e = f;
    if ((e[1] & -8) == a) goto B_ja;
    f = c >> 29;
    c = c << 1;
    d = e + (f & 4) + 16;
    f = d.a;
    if (f) continue L_ma;
  }
  a = 8;
  c = 24;
  f = e;
  label B_ka:
  e = b;
  h = b;
  goto B_ia;
  label B_ja:
  f = e[2];
  f[3] = b;
  c = 8;
  d = e + 8;
  h = 0;
  a = 24;
  label B_ia:
  d.a = b;
  (b + c)[0]:int = f;
  b[3]:int = e;
  (b + a)[0]:int = h;
  0[16645]:int = select_if(b = 0[16645]:int + -1, -1, b);
  label B_a:
}

