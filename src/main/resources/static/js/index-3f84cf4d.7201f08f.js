import{_ as $,a as j,u as k,b as I}from"./index.vue_vue_type_script_name_Preview_setup_true_lang-22e0d43b.3b2bc57b.js";import"./index.499c9c19.js";import{n as d,i as w,C as u,r as H,o as f,l as g,e as a,P,h as i,M,t as B,H as E,f as _,F as G,I as N,J as V}from"./index.3cf49b7d.js";var X=Object.defineProperty,Y=Object.defineProperties,z=Object.getOwnPropertyDescriptors,x=Object.getOwnPropertySymbols,F=Object.prototype.hasOwnProperty,U=Object.prototype.propertyIsEnumerable,C=(t,e,r)=>e in t?X(t,e,{enumerable:!0,configurable:!0,writable:!0,value:r}):t[e]=r,O=(t,e)=>{for(var r in e||(e={}))F.call(e,r)&&C(t,r,e[r]);if(x)for(var r of x(e))U.call(e,r)&&C(t,r,e[r]);return t},b=(t,e)=>Y(t,z(e));function J(t,e,r,c){return Math.atan2(t-r,e-c)*(180/Math.PI)*-1+180}const L=t=>(N("data-v-4e21238c"),t=t(),V(),t),R={class:"gradient-controls border-box flex justify-between items-center w-full mb-8px px-16px"},T={class:"flex flex-1"},q={key:0,class:"relative mr-24px"},A=L(()=>a("div",{class:"gradient-degree-pointer"},null,-1)),K=[A],Q={class:"gradient-degree-value flex justify-center items-center"},W=d({name:"GradientControls"}),Z=d(b(O({},W),{setup(t){const e=w("colorPickerState"),r=w("updateColor"),c=u(()=>e.type),m=u(()=>e.degree),v=n=>{r({type:n},"type")},l=H(!0),h=()=>{if(l.value){l.value=!1;return}let n=(e.degree||0)+45;n>=360&&(n=0),r({degree:~~n},"degree")},S=u(()=>({transform:`rotate(${e.degree}deg)`})),y=k(n=>{const o=n.target.getBoundingClientRect(),p=~~(8-window.pageYOffset)+o.top,D=~~(8-window.pageXOffset)+o.left;return{centerY:p,centerX:D}},(n,{centerX:s,centerY:o})=>{l.value=!0;const p=J(n.clientX,n.clientY,s,o);r({degree:~~p},"degree")},n=>{const s=n.target.classList;l.value=!1,s.contains("gradient-degrees")||s.contains("icon-rotate")});return(n,s)=>(f(),g("div",R,[a("div",T,[a("div",{class:P(`gradient-type-item liner-gradient ${i(c)==="linear"?"active":""}`),onClick:s[0]||(s[0]=o=>v("linear"))},null,2),a("div",{class:P(`gradient-type-item radial-gradient ${i(c)==="radial"?"active":""}`),onClick:s[1]||(s[1]=o=>v("radial"))},null,2)]),i(c)==="linear"?(f(),g("div",q,[a("div",{class:"gradient-degrees cursor-pointer flex justify-center items-center",onMousedown:s[2]||(s[2]=(...o)=>i(y)&&i(y)(...o)),onClick:h},[a("div",{class:"gradient-degree-center",style:M(i(S))},K,4)],32),a("div",Q,[a("p",null,B(i(m))+"°",1)])])):E("",!0)]))}})),ee=I(Z,[["__scopeId","data-v-4e21238c"]]),te=d({name:"Gradient"}),ce=d(b(O({},te),{setup(t){return(e,r)=>(f(),g(G,null,[_(ee),_($),_(j)],64))}}));export{ce as default};