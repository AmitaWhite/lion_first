"use client";

import { useState } from "react";

const PLACEHOLDER = "/images/product-placeholder.svg";

interface Props {
	images: string[];
	alt: string;
}

export default function ImageGallery({ images, alt }: Props) {
	const [current, setCurrent] = useState(0);
	const [hovered, setHovered] = useState(false);

	const list = images.length > 0 ? images : [PLACEHOLDER];
	const prev = () => setCurrent((current - 1 + list.length) % list.length);
	const next = () => setCurrent((current + 1) % list.length);

	return (
		<>
			{/* 메인 이미지 */}
			<div
				style={{
					position: "relative",
					width: "100%",
					aspectRatio: "4/3",
					overflow: "hidden",
					borderRadius: 16,
					background: "#f0f0f0",
				}}
				onMouseEnter={() => setHovered(true)}
				onMouseLeave={() => setHovered(false)}
			>
				<img
					src={list[current]}
					alt={alt}
					style={{
						width: "100%",
						height: "100%",
						objectFit: "cover",
						display: "block",
					}}
				/>

				{/* 좌 1/3 클릭존 */}
				{list.length > 1 && (
					<div
						onClick={prev}
						style={{
							position: "absolute",
							top: 0,
							left: 0,
							width: "33%",
							height: "100%",
							cursor: "pointer",
							zIndex: 1,
						}}
					/>
				)}
				{/* 우 1/3 클릭존 */}
				{list.length > 1 && (
					<div
						onClick={next}
						style={{
							position: "absolute",
							top: 0,
							right: 0,
							width: "33%",
							height: "100%",
							cursor: "pointer",
							zIndex: 1,
						}}
					/>
				)}

				{/* 호버 시 화살표 버튼 */}
				{hovered && list.length > 1 && (
					<>
						<button
							type="button"
							onClick={prev}
							style={{
								position: "absolute",
								top: "50%",
								left: 12,
								transform: "translateY(-50%)",
								zIndex: 2,
								width: 36,
								height: 36,
								borderRadius: "50%",
								background: "rgba(0,0,0,0.4)",
								border: "none",
								color: "white",
								fontSize: 20,
								cursor: "pointer",
								display: "flex",
								alignItems: "center",
								justifyContent: "center",
								backdropFilter: "blur(4px)",
							}}
						>
							‹
						</button>
						<button
							type="button"
							onClick={next}
							style={{
								position: "absolute",
								top: "50%",
								right: 12,
								transform: "translateY(-50%)",
								zIndex: 2,
								width: 36,
								height: 36,
								borderRadius: "50%",
								background: "rgba(0,0,0,0.4)",
								border: "none",
								color: "white",
								fontSize: 20,
								cursor: "pointer",
								display: "flex",
								alignItems: "center",
								justifyContent: "center",
								backdropFilter: "blur(4px)",
							}}
						>
							›
						</button>
					</>
				)}

				{/* 점 인디케이터 */}
				{list.length > 1 && (
					<div
						style={{
							position: "absolute",
							bottom: 10,
							left: "50%",
							transform: "translateX(-50%)",
							display: "flex",
							gap: 6,
							zIndex: 2,
						}}
					>
						{list.map((_, i) => (
							<span
								key={i}
								style={{
									width: 7,
									height: 7,
									borderRadius: "50%",
									background:
										i === current ? "white" : "rgba(255,255,255,0.45)",
									display: "block",
									transition: "background 0.2s",
								}}
							/>
						))}
					</div>
				)}
			</div>

			{/* 썸네일 목록 */}
			{list.length > 1 && (
				<div
					style={{ display: "flex", gap: 8, marginTop: 10, flexWrap: "wrap" }}
				>
					{list.map((url, i) => (
						<button
							key={i}
							type="button"
							onClick={() => setCurrent(i)}
							style={{
								width: 64,
								height: 64,
								padding: 0,
								borderRadius: 10,
								overflow: "hidden",
								border:
									i === current
										? "2px solid var(--color-primary)"
										: "2px solid transparent",
								cursor: "pointer",
								opacity: i === current ? 1 : 0.55,
								transition: "opacity 0.2s, border 0.2s",
								background: "none",
							}}
						>
							<img
								src={url}
								alt={`${alt} ${i + 1}`}
								style={{ width: "100%", height: "100%", objectFit: "cover" }}
							/>
						</button>
					))}
				</div>
			)}
		</>
	);
}
