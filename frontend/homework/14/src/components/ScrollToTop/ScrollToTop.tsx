import { CSSProperties, useRef } from "react";
import { para, scrollToTop } from "./ScrollToTop.styles";

function ScrollToTop() {
  const scrollToTopRef = useRef<HTMLDivElement>(null);

  const scroll = () => {
    if (scrollToTopRef.current) {
      scrollToTopRef.current.scrollTo({ top: 0, behavior: "smooth" });
    }
  };

  const styleList: { [key: string]: CSSProperties } = {
    paragraph: {
      width: "100px",
    },
  };

  return (
    <div ref={scrollToTopRef} style={scrollToTop}>
      <p style={para}>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi voluptas
        ea repellat dolore. Quas voluptatum perspiciatis, itaque iusto aliquid
        molestiae eos porro quam voluptate unde! Consequatur impedit repudiandae
        repellendus? Quae dolore non reprehenderit. Porro tenetur nulla ratione
        dolore. Officiis laborum quidem molestiae facilis reprehenderit
        asperiores neque culpa! Corrupti inventore quae blanditiis aspernatur!
        Aliquam, harum veritatis! Voluptatem deserunt nulla ab velit veniam
        possimus eum itaque fugiat expedita excepturi dolorem repellat nam
        veritatis nisi eius non ea voluptas, odio praesentium molestias
        recusandae reiciendis consectetur magni tempore! Autem aliquid modi,
        iusto accusamus recusandae explicabo architecto eveniet sunt fugiat nam
        dolorum quae ipsa quis excepturi? Iste minima sapiente vitae maiores vel
        dolores consequuntur dicta nisi quisquam ullam maxime dolorem eligendi
        quidem, excepturi tenetur magnam. Voluptatem aspernatur id est officiis
        dolores. Adipisci perferendis vel nam minus sit nobis ratione asperiores
        ducimus in vitae facilis inventore at autem, incidunt rem error quod
        delectus quas velit modi explicabo eum fugiat aut excepturi. Delectus
        aut ratione earum! Adipisci nisi, sunt itaque, ex ad culpa beatae
        consectetur fugiat nostrum, velit aspernatur unde. Ratione, eligendi
        deserunt quis quaerat aliquid magni beatae non itaque accusamus, et
        harum. Minima dolorem, facere, facilis quos illo eveniet id laborum,
        perspiciatis eos dolor inventore sint cum maiores iure? Inventore
        aperiam dolorem illo, nihil, maxime provident voluptatum neque veniam
        quod rerum, voluptate omnis perspiciatis. Error sapiente ad
        reprehenderit veritatis animi delectus neque autem earum iusto
        quibusdam. Omnis corporis doloremque quisquam! Quam sapiente architecto
        enim fugiat recusandae optio et velit inventore suscipit repudiandae
        consequuntur cum hic sequi ipsum animi dolorem, aliquam saepe accusamus
        a sit iusto dicta quae impedit vel. Dignissimos, impedit cupiditate?
        Rerum aut consequuntur quibusdam voluptatem beatae modi sapiente
        doloribus est. Odit quas a facere sapiente, ipsam minima adipisci
        perferendis, et sit sed vel obcaecati soluta labore! Maiores tempora
        porro tempore. Deleniti ut repellat eligendi tempora laboriosam
        accusantium explicabo culpa doloremque atque, unde repudiandae quia sunt
        optio asperiores harum, deserunt voluptatem nihil recusandae itaque sed
        aperiam? Cupiditate, minus. Adipisci veritatis aperiam corporis minus
        nesciunt, ad vitae dignissimos voluptates asperiores exercitationem
        voluptate sit deserunt numquam ex eaque fugiat temporibus unde
        aspernatur! Est impedit quae in vitae ea ab distinctio dignissimos quis,
        repellendus amet illo atque aliquid, optio odio ex natus, ad cumque!
        Reprehenderit maxime repellendus, adipisci laboriosam, vero fugit
        dolorum voluptatibus similique aliquid veniam quod excepturi neque
        voluptas sed! Laboriosam, quibusdam incidunt! Ipsam atque repellendus
        saepe ea quaerat quisquam quos exercitationem repellat, impedit
        praesentium similique laboriosam amet nobis cumque nisi suscipit error
        libero magni dolore natus recusandae vel? Quod laborum, provident
        similique iste magnam sint laudantium odit temporibus amet blanditiis
        doloremque dolorem quibusdam odio fuga quo perspiciatis sed, perferendis
        exercitationem cum accusamus? Eligendi ut asperiores voluptatem deleniti
        id praesentium natus pariatur! Voluptates molestiae fuga aspernatur
        itaque consequuntur ad earum vitae saepe maiores corporis, vel harum
        obcaecati omnis labore ipsam impedit. Quasi suscipit eos pariatur
        tempore nemo aliquam natus similique repellat cumque corporis neque ipsa
        necessitatibus maxime quam iusto, nam, doloremque nulla debitis
        asperiores. Deleniti alias voluptas odio laboriosam provident enim nulla
        facilis exercitationem nobis, autem quos eligendi illo! Aut harum
        doloremque rerum iure porro quis expedita eum? Nemo quam ullam ipsum
        consequatur cupiditate optio error nisi quod consequuntur maiores, et
        quidem dolorum molestiae quibusdam explicabo animi neque quaerat
        repellat tenetur eos, esse quasi. Nobis sint voluptatum officia iste
        vel, quia magni labore natus quisquam, eos, sed quidem rem quae iure
        itaque temporibus! Enim tempora dicta officiis nihil natus reprehenderit
        nostrum quod molestiae possimus consequatur ut delectus repellat facere
        sed quis, cumque itaque esse? Libero, quia. Quas commodi pariatur aut
        qui quam enim beatae labore, aliquam impedit nulla est officia eum
        consectetur odit officiis atque id dolorem praesentium vero? Nostrum
        iusto eaque amet facere non consequuntur dolorum ducimus, rem sit nulla
        maxime voluptates! Deserunt, rem ut? Magni nihil omnis laudantium
        consectetur? Beatae numquam quia impedit est nobis, ullam sed et
        nostrum? Fugiat optio, voluptate quos sunt harum pariatur nulla sequi
        vero facilis veniam ex, molestiae ducimus eos ipsa voluptas consectetur
        consequatur iste ullam repudiandae culpa et laudantium vitae dolores!
        Dicta esse exercitationem voluptate similique quisquam asperiores harum,
        voluptas nemo cum quasi pariatur veniam consequatur, dolore consequuntur
        aliquam mollitia quidem recusandae! Iste repellat, quam itaque sapiente
        esse cumque. Officia, doloribus. At praesentium vitae dignissimos quis,
        nisi provident animi? Esse quos alias quas. Ullam asperiores rerum
        ratione, quia quam error veniam repellendus dolores ipsam iste nobis
        amet voluptatem aspernatur numquam quo quaerat, esse similique. Sed ipsa
        dolore repellendus culpa magnam est harum, provident corrupti nam
        nostrum ipsam iure! Maiores quo asperiores doloremque quasi facere odio
        rerum neque, cum fuga earum nesciunt nobis est, blanditiis omnis
        perferendis architecto soluta. Ipsam nesciunt quas possimus quo
        consequuntur rerum dolores aperiam inventore voluptatem eaque explicabo
        quasi accusamus, illum, cupiditate dolore! Necessitatibus nisi cum saepe
        expedita id eius commodi, minima earum distinctio ipsa, dignissimos
        explicabo quisquam! Iure, doloremque ab vero deserunt optio ipsa
        aliquam, excepturi officia non quod rerum architecto nostrum enim
        beatae, mollitia assumenda! Tempora ullam laudantium impedit odio error
        aut saepe atque quasi, totam veniam pariatur iusto cumque numquam quo,
        eligendi, suscipit culpa voluptatibus. Vitae quis fuga recusandae
        pariatur, et aut culpa quae molestias nemo rerum molestiae possimus
        explicabo qui, exercitationem libero sint esse illum cum suscipit
        repellat sed tempora cupiditate. Neque eum at, nesciunt voluptatum
        repellat porro qui omnis? Ducimus quod ab cum atque similique ipsam
        molestias veniam aperiam minima sit! Quidem repellendus repellat
        voluptatibus sunt ipsum voluptatem rerum dolorem fuga neque accusantium
        soluta quos culpa nihil magni quaerat facere autem veniam, eos
        consequatur non esse odio laborum dolore inventore. Sapiente, laborum!
        Alias nemo fuga nisi rerum praesentium quia deserunt minus ipsum
        architecto soluta? Deleniti earum qui vitae officia saepe harum
        praesentium, perferendis odio reprehenderit reiciendis accusamus
        exercitationem debitis dignissimos, itaque voluptatem voluptate ea?
        Quae, quas nihil temporibus blanditiis sapiente est, hic fuga dolores
        non dolorem corrupti accusamus nulla repudiandae commodi expedita.
        Provident vitae sit autem nobis rerum, distinctio ducimus, natus minima,
        illum soluta quae accusantium fuga? Laudantium laborum facere officiis
        doloremque obcaecati dolor officia eaque nam corrupti minima nostrum
        veniam ipsa, laboriosam quae quasi fuga saepe repellat reiciendis
        repudiandae impedit.
      </p>
      <button onClick={scroll}>Scroll To Top</button>
    </div>
  );
}

export default ScrollToTop;
